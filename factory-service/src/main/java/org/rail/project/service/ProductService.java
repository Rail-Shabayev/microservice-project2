package org.rail.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.rail.project.dto.ProductDto;
import org.rail.project.event.ProductMadeEvent;
import org.rail.project.exception.ProductNotFoundException;
import org.rail.project.mapper.ProductMapper;
import org.rail.project.model.Product;
import org.rail.project.repository.ManufacturerRepository;
import org.rail.project.repository.ProductRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@CacheConfig(cacheNames = {"product"})
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate kafkaTemplate;
    private final ManufacturerRepository manufacturerRepository;

    @Transactional(readOnly = true)
    @CachePut
    public List<ProductDto> fetchProducts() {
        return productRepository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }

    public String saveProduct(ProductDto productDto) {
        Product product = mapper.mapToEntity(productDto);
        product.setDateCreated(LocalDate.now());
        if (manufacturerRepository.findAll().isEmpty()) {
            throw new RuntimeException("No manufacturer in the table");
        }
        productRepository.save(product);
        ProductMadeEvent productMadeEvent = new ProductMadeEvent(productDto.getName(), productDto.getDateCreated(), productDto
                .getPrice());
        kafkaTemplate.send("2notificationTopic", productMadeEvent);
        return "product saved";
    }


    @CachePut(key = "#id")
    public String putProduct(Long id, ProductDto productDto) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        Product mappedToEntity = mapper.mapToEntity(productDto);
        mappedToEntity.setDateCreated(product.getDateCreated());
        mappedToEntity.setId(product.getId());
        productRepository.save(mappedToEntity);
        return "product updated";
    }
//    {"op":"replace","path":"/telephone","value":"+1-555-56"},
//    {"op":"add","path":"/favorites/0","value":"Bread"}
//    what the fuck did I do
    @CachePut(key = "#id")
    public String patchProduct(Long id, JsonPatch jsonPatch) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found with id: " + id)
        );
        Product productPatched;
        try {
            productPatched = applyPatchToProduct(jsonPatch, product);
        } catch (JsonPatchException | JsonProcessingException e1) {
            throw new RuntimeException(e1);
        }
        productRepository.save(productPatched);
        return "product updated";
    }

    private Product applyPatchToProduct(JsonPatch jsonPatch, Product product) throws JsonPatchException, JsonProcessingException {
        objectMapper.findAndRegisterModules();
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(product, JsonNode.class));
        return objectMapper.treeToValue(patched, Product.class);
    }

    @Transactional(readOnly = true)
    @CachePut
    public ProductDto fetchProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found with id: " + id)
        );
        return mapper.mapToDto(product);
    }

    @CacheEvict(key = "#id")
    public String deleteProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
        return "product deleted";
    }
}
