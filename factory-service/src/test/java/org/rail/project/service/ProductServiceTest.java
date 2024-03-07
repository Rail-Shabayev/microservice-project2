package org.rail.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rail.project.dto.ProductDto;
import org.rail.project.exception.ProductNotFoundException;
import org.rail.project.mapper.ProductMapper;
import org.rail.project.model.Manufacturer;
import org.rail.project.model.Product;
import org.rail.project.repository.ProductRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private Product product;
    @InjectMocks
    private ProductService productService;
    private ProductDto productDto;

    @Captor
    ArgumentCaptor<Product> argumentCaptor;

    JsonPatch patch;

    @BeforeEach
    void setUp() throws IOException {
//        productService = new ProductService(productRepository, productMapper);
        productDto = new ProductDto("rail", new BigDecimal(23), LocalDate.now(),
                new Manufacturer(1L, "man", null, null, null));
        product = new Product(1L, "rail",
                new BigDecimal(23), LocalDate.now(),
                new Manufacturer(1L, "man", null, null, null));
        String json = "[{\"op\":\"replace\",\"path\":\"/name\",\"value\":\"rail2\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream stream = new ByteArrayInputStream(json.getBytes());
        patch = objectMapper.readValue(stream, JsonPatch.class);
    }

    @Test
    @DisplayName("Get all products from db without an error")
    void fetchProductsTest() {
        // Arrange
        List<Product> actualReturn = List.of(product);
        // Act
        when(productRepository.findAll()).thenReturn(actualReturn);
        when(productMapper.mapToDto(any(Product.class))).thenReturn(productDto);
        // Hamcrest assertion comparing two matchers
        // Assert
        assertIterableEquals(List.of(productDto), productService.fetchProducts());
    }

    @Test
    @DisplayName("successfully save given ProductDto object")
    void saveProduct() {
        // Arrange
        // Act
        when(productMapper.mapToEntity(productDto)).thenReturn(product);
        // Assert
        assertThat(productService).returns("product saved",
                productService1 -> productService1.saveProduct(productDto));
    }

    @Test
    @DisplayName("should PUT given productDto object ")
    void shouldPutProduct() throws ProductNotFoundException {
        //Arrange
        Product product1 = new Product();
        //Act
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productMapper.mapToEntity(productDto)).thenReturn(product);
        //Assert
        assertEquals("product updated", productService.putProduct(1L, productDto));
        verify(productRepository, times(1)).save(argumentCaptor.capture());
        assertNotSame(product1.getName(), argumentCaptor.getValue().getName());
    }

    @Test
    @DisplayName("should partially Patch old productDto to the new one")
    void shouldPatchProduct() throws ProductNotFoundException {
        // Arrange
        // Act
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        product.setName("rail2");
        productService.patchProduct(1L, patch);
        // Assert
        verify(productRepository, times(1)).save(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getName(), "rail2");
    }

    @Test
    @DisplayName("get product by id")
    void fetchProductById() throws ProductNotFoundException {
        //Arrange
        //Act
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(productMapper.mapToDto(product)).thenReturn(productDto);
        //Assert
        ProductDto expectedDto = productService.fetchProductById(1L);
        assertEquals(expectedDto, productDto);
    }

    @Test
    @DisplayName("value from db doesn't equal given value")
    void couldNotFetchProductById() throws ProductNotFoundException {
        //Arrange
        //Act
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(productMapper.mapToDto(product)).thenReturn(any(ProductDto.class));
        //Assert
        ProductDto emptyProductDto = productService.fetchProductById(1L);
        assertNotEquals(emptyProductDto, productDto);
    }

    @Test
    @DisplayName("should delete product")
    void deleteProduct() throws ProductNotFoundException {
        //Arrange
        //Act
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        //Assert
        assertThat(productService.deleteProduct(1L)).isEqualTo("product deleted");
    }

    @Test
    @DisplayName("throws notFoundProductException")
    void notFoundProductToDelete() {
        //Arrange
        //Act
        //Assert
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1L));
    }
}