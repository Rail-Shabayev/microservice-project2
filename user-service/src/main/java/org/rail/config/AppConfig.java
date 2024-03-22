package org.rail.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "User service",
                version = "1.0",
                contact = @Contact(
                        name = "Rail Sha",
                        url = "https://github.com/Rail-Shabayev")),
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080"
                )
        })
public class AppConfig {
}
