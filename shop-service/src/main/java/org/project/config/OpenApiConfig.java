package org.project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Class that contains general information for swagger-ui
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Shop service",
                contact = @Contact(
                        name = "Rail Shabayev",
                        url = "https://github.com/Rail-Shabayev"
                ),
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}

