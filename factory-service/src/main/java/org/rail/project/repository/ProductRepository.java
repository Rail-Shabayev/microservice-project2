package org.rail.project.repository;

import org.rail.project.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "shipper")
    List<Product> findAll();

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "shipper")
    Optional<Product> findById(Long aLong);

}
