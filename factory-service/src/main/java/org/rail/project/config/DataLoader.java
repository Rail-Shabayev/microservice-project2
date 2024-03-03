package org.rail.project.config;

import org.rail.project.model.Manufacturer;
import org.rail.project.repository.ManufacturerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner runner(ManufacturerRepository manufacturerRepository) {
        return args -> {
            Manufacturer manufacturer = Manufacturer.builder()
                    .id(1L)
                    .shipperName("Aeroflot")
                    .email("arbat1@aeroflot.ru")
                    .phoneNumber("+1 (415) 555‑0132")
                    .build();
            Manufacturer manufacturer2 = Manufacturer.builder()
                    .id(2L)
                    .shipperName("Navalny")
                    .email("navalny@super.ru")
                    .phoneNumber("+1 (415) 555‑0132")
                    .build();
            manufacturerRepository.save(manufacturer);
            manufacturerRepository.save(manufacturer2);
        };
    }
}
