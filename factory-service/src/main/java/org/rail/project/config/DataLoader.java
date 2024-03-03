package org.rail.project.config;

import org.rail.project.model.Shipper;
import org.rail.project.repository.ShipperRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner runner(ShipperRepository shipperRepository) {
        return args -> {
            Shipper shipper = Shipper.builder()
                    .id(1L)
                    .shipperName("Aeroflot")
                    .email("arbat1@aeroflot.ru")
                    .phoneNumber("+1 (415) 555‑0132")
                    .build();
            Shipper shipper2 = Shipper.builder()
                    .id(2L)
                    .shipperName("Navalny")
                    .email("navalny@super.ru")
                    .phoneNumber("+1 (415) 555‑0132")
                    .build();
            shipperRepository.save(shipper);
            shipperRepository.save(shipper2);
        };
    }
}
