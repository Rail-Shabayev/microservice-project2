package org.project.config;

import org.hibernate.usertype.BaseUserTypeSupport;
import org.project.model.Shipper;
import org.project.repository.OrderRepository;
import org.project.repository.ShipperRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class persisting dummy data for testing purposes
 */
@Configuration
public class DataLoader {

    /**
     * method that creates and saves product objects
     *
     * @param shipperRepository {@link OrderRepository} used to save product objects in database
     * @return {@link CommandLineRunner} object
     */
    @Bean
    CommandLineRunner runner(ShipperRepository shipperRepository) {
        return args -> {
            Shipper shipper = Shipper.builder()
                    .id(1L)
                    .shipperName("shipper1")
                    .build();
            shipperRepository.save(shipper);
        };
    }
}
