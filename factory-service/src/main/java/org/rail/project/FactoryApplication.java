package org.rail.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FactoryApplication.class, args);
    }
}
