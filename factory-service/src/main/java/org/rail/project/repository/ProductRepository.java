package org.rail.project.repository;

import jakarta.persistence.LockModeType;
import org.rail.project.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @Lock(LockModeType.NONE)
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "manufacturer")
    List<Product> findAll();

    @Override
    @Lock(LockModeType.NONE)
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "manufacturer")
    Optional<Product> findById(Long aLong);

}
