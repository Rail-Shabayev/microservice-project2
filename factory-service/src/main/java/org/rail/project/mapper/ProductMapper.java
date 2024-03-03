package org.rail.project.mapper;

import org.rail.project.dto.ProductDto;
import org.rail.project.model.Product;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapper {

    public Product mapToEntity(ProductDto productDto) {
        return Product.builder()
                .price(productDto.getPrice())
                .name(productDto.getName())
                .dateCreated(productDto.getDateCreated())
                .manufacturer(productDto.getManufacturer())
                .build();
    }

    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .price(product.getPrice())
                .name(product.getName())
                .dateCreated(product.getDateCreated())
                .manufacturer(product.getManufacturer())
                .build();
    }

}