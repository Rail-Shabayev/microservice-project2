package org.project.service;

import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.dto.OrderDto;
import org.project.exception.OrderNotFoundException;
import org.project.mapper.OrderMapper;
import org.project.model.Order;
import org.project.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderMapper mapper;
    @InjectMocks
    OrderService orderService;


    Order order = Instancio.of(Order.class)
            .set(field(Order::getId), 1L)
            .create();
    OrderDto orderDto = Instancio.create(OrderDto.class);


    @Test
    void testGetAllOrders() {
        //Arrange
        //Act
        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(mapper.mapToDto(any(Order.class))).thenReturn(orderDto);
        List<OrderDto> result = orderService.getAllOrders();
        //Assert
        Assertions.assertEquals(List.of(orderDto), result);
    }

    @Test
    void testSaveOrder() {
        when(mapper.mapToEntity(any())).thenReturn(order);
        when(orderRepository.save(any())).thenReturn(order);

        String result = orderService.saveOrder(orderDto);
        Assertions.assertEquals("order saved", result);
    }

    @Test
    void testPutOrder() throws OrderNotFoundException {
        when(orderRepository.save(any())).thenReturn(order);
        when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(order));
        when(mapper.mapToEntity(any())).thenReturn(order);

        String result = orderService.putOrder(1L, orderDto);
        Assertions.assertEquals("order updated", result);
    }

    @Test
    void testDeleteOrder() throws OrderNotFoundException {
        when(orderRepository.findById(1L)).thenReturn(Optional.ofNullable(order));

        String result = orderService.deleteOrder(1L);
        Assertions.assertEquals("order deleted", result);
    }
}
