package org.rail.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.rail.project.dto.ProductDto;
import org.rail.project.exception.ProductNotFoundException;
import org.rail.project.mapper.ProductMapper;
import org.rail.project.model.Product;
import org.rail.project.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional(readOnly = true)
    public List<ProductDto> fetchProducts() {
        return productRepository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }

    public String saveProduct(ProductDto productDto) {
        Product product = mapper.mapToEntity(productDto);
        product.setDateCreated(LocalDate.now());
        productRepository.save(product);
        return "product saved";
    }


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
    public String patchProduct(Long id, JsonPatch jsonPatch) throws ProductNotFoundException{
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
    public ProductDto fetchProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found with id: " + id)
        );
        return mapper.mapToDto(product);
    }

    public String deleteProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
        return "product deleted";
    }
}
