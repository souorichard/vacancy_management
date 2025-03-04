package org.richard.vacancy_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "Vacancies Management",
    description = "API responsible for managing vacancies",
    version = "1"
  )
)
@SecurityScheme(
  name = "jwt_auth",
  scheme = "bearer",
  bearerFormat = "JWT",
  type = SecuritySchemeType.HTTP,
  in = SecuritySchemeIn.HEADER
)
public class VacancyManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(VacancyManagementApplication.class, args);
  }
}
