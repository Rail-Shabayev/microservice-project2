package org.project.config;

import org.project.model.*;
import org.project.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Class persisting dummy data for testing purposes
 */
@Configuration
public class DataLoader {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8080/user/api/users")
            .build();

    /**
     * method that creates and saves product objects
     *
     * @param shipperRepository {@link OrderRepository} used to save product objects in database
     * @return {@link CommandLineRunner} object
     */
    @Bean
    CommandLineRunner runner(ShipperRepository shipperRepository,
                             UserRepository userRepository,
                             ProductRepository productRepository,
                             OrderDetailsRepository orderDetailsRepository,
                             OrderRepository orderRepository) {
        return args -> {
            Shipper shipper = Shipper.builder()
                    .id(1L)
                    .shipperName("shipper1")
                    .build();
            shipperRepository.save(shipper);
            List<User> users = restClient.get()
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            if (users != null) {
                userRepository.saveAll(users);
            }
            Product product = Product.builder()
                    .dateCreated(LocalDate.now())
                    .name("prod")
                    .price(new BigDecimal(23))
                    .manufacturer(null)
                    .orderDetails(List.of(new OrderDetails()))
                    .build();
            productRepository.save(product);
            Order order = Order.builder()
                    .user(users.get(1))
                    .status(Status.SEND)
                    .shipper(shipper)
                    .deliveryDate(LocalDate.now())
                    .dateCreated(LocalDate.now())
                    .orderDetails(null)
                    .build();
            orderRepository.save(order);
            OrderDetails orderDetails = new OrderDetails(1L, order, product, new BigDecimal(38), 2);
            orderDetailsRepository.save(orderDetails);
        };
    }
}
