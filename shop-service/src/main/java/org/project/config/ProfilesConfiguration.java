package org.project.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ProfilesConfiguration {

    @Bean
    @ConditionalOnProperty(prefix="service.docker", name = "environment", havingValue = "true")
    RestClient restClientDocker() {
        return RestClient.builder()
                .baseUrl("http://api-gateway:8080/user/api/users")
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix="service.docker", name = "environment", havingValue = "false")
    RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8080/user/api/users")
                .build();
    }
}
