package org.project.repository;

import org.project.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * OrderRepository interface for persisting data into db.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @EntityGraph(attributePaths = {"shipper", "user", "orderDetails.product"}, type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Order> findAll();

}
