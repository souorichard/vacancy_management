package org.richard.vacancy_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
  
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("Vacancies Management")
        .description("API responsible for managing vacancies")
        .version("1")
      )
      .schemaRequirement("jwt_auth", createSecurityScheme());
  }

  private SecurityScheme createSecurityScheme() {
    return new SecurityScheme()
      .name("jwt_auth")
      .scheme("bearer")
      .bearerFormat("JWT")
      .type(SecurityScheme.Type.HTTP)
      .in(SecurityScheme.In.HEADER);
  }
}
