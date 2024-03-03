package org.project.controller;

import lombok.RequiredArgsConstructor;
import org.project.dto.OrderDto;
import org.project.exception.OrderNotFoundException;
import org.project.model.Order;
import org.project.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Controller class that holds CRUD operations for product entity in /api/product endpoint
 * <p>
 *
 */
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController extends OrderApi {
    /**
     * Order service object for working with business logic.
     */
    private final OrderService orderService;

    /**
     * GET method for /api/Order endpoint
     * @return list of found {@link OrderDto} objects
     */
    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    /**
     * POST method for /api/order endpoint
     * @param orderDto {@link OrderDto} object passed by a user
     * @return string with operation work status
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public String postOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    /**
     * PUT method for /api/order/ endpoint
     * @param orderDto orderDto {@link OrderDto} object passed by a user
     * @param id id of the {@link Order} that user wants to update
     * @return string with operation work status
     * @throws OrderNotFoundException if {@link Order} with provided id is not found in database
     */
    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public String putOrder(@RequestBody OrderDto orderDto, @PathVariable("id") Long id) throws OrderNotFoundException {
        return orderService.putOrder(id, orderDto);
    }

    /**
     * DELETE method for /api/order/ endpoint
     * @param id name of the {@link Order} that user wants to delete
     * @return string with operation work status
     * @throws OrderNotFoundException if {@link Order} with provided name is not found in database
     */
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Long id) throws OrderNotFoundException {
        return orderService.deleteOrder(id);
    }
}
