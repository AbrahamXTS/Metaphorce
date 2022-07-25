package com.metaphorce.rrhh.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI metaphorceOpenAPI() {

        List<SecurityRequirement> securityRequirements = new ArrayList<SecurityRequirement>();
        securityRequirements.add(new SecurityRequirement().addList("Auth"));

        return new OpenAPI()
            .info(new Info()
                .title("Metaphorce RRHH")
                .description("Springboot API for the Metaphorce RRHH Test.")
                .version("v1.0.0")
            .license(new License()
                .name("Apache 2.0")
                .url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                .description("Made by Abraham Espinosa")
                .url("https://github.com/AbrahamXTS/Metaphorce"))
            .components(new Components()
                .addSecuritySchemes("JWT Token", new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }
}
