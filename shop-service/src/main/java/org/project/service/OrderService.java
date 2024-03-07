package org.project.service;

import lombok.RequiredArgsConstructor;
import org.project.dto.OrderDto;
import org.project.exception.OrderNotFoundException;
import org.project.mapper.OrderMapper;
import org.project.model.Order;
import org.project.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Class separating presentation layer from persistent layer of product
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    /**
     * property for working with persistent layer
     */
    private final OrderRepository orderRepository;

    /**
     * property for mapping {@link Order} and {@link OrderDto} class
     */
    private final OrderMapper mapper;

    /**
     * searching for all products in the database
     * @return list of {@link OrderDto}
     */
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }

    /**
     * saves passed {@link OrderDto} object to the database
     * @param orderDto {@link OrderDto} object that was passed by the user
     * @return status of method work
     */
    public String saveOrder(OrderDto orderDto) {
        Order order = mapper.mapToEntity(orderDto);
        order.setDateCreated(LocalDate.now());

        orderRepository.save(order);
        return "order saved";
    }

    /**
     * changes existing {@link Order} object to new one
     * @param id name of the {@link Order} to be changed
     * @param orderDto {@link OrderDto} object that was passed by the user
     * @return status of method work
     * @throws OrderNotFoundException if {@link Order} with provided id is not found in database
     */
    public String putOrder(Long id, OrderDto orderDto) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        Order mappedToEntity = mapper.mapToEntity(orderDto);
        mappedToEntity.setDeliveryDate(LocalDate.now());
        mappedToEntity.setDateCreated(order.getDateCreated());
        orderRepository.save(mappedToEntity);
        return "order updated";
    }

    /**
     * deletes {@link Order} from the database
     * @param id name of the {@link Order} that user wants to delete
     * @return string with operation work status
     * @throws OrderNotFoundException if {@link Order} with provided id is not found in database
     */
    public String deleteOrder(Long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
        return "order deleted";
    }
}
