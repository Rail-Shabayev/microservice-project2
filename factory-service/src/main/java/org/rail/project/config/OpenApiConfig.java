package org.rail.project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(version = "1.0",
                summary = "Open Api definition of factory service",
                title = "Factory service",
                contact = @Contact(name = "rail", url = "https://github.com/Rail-Shabayev")),
        servers = {
                @Server(url = "http://locahost:8080",
                        description = "default server of the service")
        }

)
public class OpenApiConfig {
}
