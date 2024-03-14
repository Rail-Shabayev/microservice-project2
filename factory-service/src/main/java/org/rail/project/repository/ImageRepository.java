package org.rail.project.repository;

import org.rail.project.model.ProductImage;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ProductImage, Long> {
}
