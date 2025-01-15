package com.trips.bookingservice.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("Trips: booking-service REST APIs")
                .description("API for Booking Services. Please add 'Bearer ' for accessing APIs")
                .version("v1")
                .contact(
                        new Contact()
                                .name("Krishnakumar")
                                .url("https://github.com/krishnakumar-ng")
                                .email("krishnakumargunavathi@gmail.com"))
                .license(new License().name("trips internal use."));
    }
}
