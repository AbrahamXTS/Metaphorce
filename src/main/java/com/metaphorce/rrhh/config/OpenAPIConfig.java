package com.metaphorce.rrhh.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Metaphorce")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI metaphorceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Metaphorce RRHH")
                .description("Springboot API for the Metaphorce RRHH Test.")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("Made by Abraham Espinosa")
                .url("https://github.com/AbrahamXTS/Metaphorce"));
    }
}