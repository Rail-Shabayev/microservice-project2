package org.rail.project.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.rail.project.dto.ProductDto;
import org.rail.project.exception.ProductNotFoundException;
import org.rail.project.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController implements ProductControllerApi {
    private final ProductService productService;

    @GetMapping
    @Override
    public List<ProductDto> getAllProducts() {
        return productService.fetchProducts();
    }

    @GetMapping("/{id}")
    @Override
    public ProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.fetchProductById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String postProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String putProduct(@RequestBody ProductDto productDto, @PathVariable Long id) throws ProductNotFoundException {
        return productService.putProduct(id, productDto);
    }

    @Override
    @GetMapping("/criteria")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProductsWithCriteria() {
        return productService.getProductsWithCriteria();
    }

    @Override
    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    @ResponseStatus(HttpStatus.CREATED)
    public String patchProduct(@PathVariable Long id, @RequestBody JsonPatch jsonPatch) throws ProductNotFoundException {
        return productService.patchProduct(id, jsonPatch);
    }

    @Override
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProduct(id);
    }
}
