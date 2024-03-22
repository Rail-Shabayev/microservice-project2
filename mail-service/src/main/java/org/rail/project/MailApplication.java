package org.rail.project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Mail service",
                version = "1",
                contact = @Contact(
                        name = "Rail Sha",
                        url = "https://github.com/Rail-Shabayev")),
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080"
                )
        })
public class MailApplication {
    public static void main(String[] args) {
       SpringApplication.run(MailApplication.class);
    }
}