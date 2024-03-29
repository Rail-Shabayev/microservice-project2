package org.project.config;

import lombok.RequiredArgsConstructor;
import org.project.model.Manufacturer;
import org.project.model.Shipper;
import org.project.model.User;
import org.project.repository.ManufacturerRepository;
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
@RequiredArgsConstructor
public class DataLoader {

    private final RestClient restClient;

    /**
     * method that creates and saves product objects
     *
     * @param shipperRepository {@link OrderRepository} used to save product objects in database
     * @return {@link CommandLineRunner} object
     */
    @Bean
    CommandLineRunner runner(ShipperRepository shipperRepository,
                             UserRepository userRepository,
                             ManufacturerRepository  manufacturerRepository) {
        return args -> {
            List<User> users = restClient.get()
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
            Shipper shipper = Shipper.builder()
                    .id(1L)
                    .shipperName("shipper1")
                    .build();
            Manufacturer manufacturer = Manufacturer.builder()
                    .id(1L)
                    .shipperName("Navalny")
                    .email("navalny@super.ru")
                    .phoneNumber("+1 (415) 555‑0132")
                    .build();
            shipperRepository.save(shipper);
            manufacturerRepository.save(manufacturer);
            userRepository.saveAll(users);
        };
    }
}
