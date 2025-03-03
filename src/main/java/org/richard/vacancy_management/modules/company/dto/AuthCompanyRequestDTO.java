package org.richard.vacancy_management.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyRequestDTO {

  private String username;
  private String password;
}
