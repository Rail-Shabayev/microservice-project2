package org.project.repository;

import org.project.model.Shipper;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @EntityGraph(attributePaths = {"orderList"})
    @Override
    List<Shipper> findAll();
}
