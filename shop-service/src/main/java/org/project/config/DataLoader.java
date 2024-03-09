package org.project.config;

import org.project.model.Shipper;
import org.project.model.User;
import org.project.repository.OrderRepository;
import org.project.repository.ShipperRepository;
import org.project.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

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
                             UserRepository userRepository) {
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
        };
    }
}
