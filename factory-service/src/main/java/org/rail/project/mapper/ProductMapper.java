package org.rail.project.mapper;

import org.rail.project.dto.ProductDto;
import org.rail.project.model.Product;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapper {

    public Product mapToEntity(ProductDto productDto) {
        return Product.builder()
                .deliveryDate(productDto.getDeliveryDate())
                .status(productDto.getStatus())
                .dateCreated(productDto.getDateCreated())
                .shipperId(productDto.getShipperId())
                .build();
    }

    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .deliveryDate(product.getDeliveryDate())
                .status(product.getStatus())
                .dateCreated(product.getDateCreated())
                .shipperId(product.getShipperId())
                .build();
    }

}