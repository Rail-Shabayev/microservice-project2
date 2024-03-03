package org.project.mapper;

import org.project.dto.OrderDto;
import org.project.model.Order;
import org.springframework.context.annotation.Configuration;


/**
 * class responsible to map productDto to product and vise versa
 */
@Configuration
public class OrderMapper {

    /** maps {@link OrderDto} to {@link Order} object
     * @param orderDto {@link OrderDto} object that needs to be mapped to {@link Order} else it saves {@link OrderDto} with old uuid as in PUT request
     * @return {@link Order} object
     */
    public Order mapToEntity(OrderDto orderDto) {
        return Order.builder()
                .shipper(orderDto.getShipper())
                .status(orderDto.getStatus())
                .deliveryDate(orderDto.getDeliveryDate())
                .dateCreated(orderDto.getDateCreated())
                .build();
    }

    /**
     * maps {@link Order}  to {@link OrderDto} object
     * @param order {@link Order} object that needs to be mapped to {@link OrderDto}
     * @return {@link OrderDto} object
     */
    public OrderDto mapToDto(Order order) {
        return OrderDto.builder()
                .shipper(order.getShipper())
                .status(order.getStatus())
                .dateCreated(order.getDateCreated())
                .deliveryDate(order.getDeliveryDate())
                .build();
    }

}
