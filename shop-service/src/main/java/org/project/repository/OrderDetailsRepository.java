package org.project.repository;

import org.project.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
}
