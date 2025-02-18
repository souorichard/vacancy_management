package org.richard.vacancy_management.modules.company.controllers;

import org.richard.vacancy_management.modules.company.dto.AuthCompanyDTO;
import org.richard.vacancy_management.modules.company.use_cases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
  
  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/company")
  public ResponseEntity<Object> create(@Valid @RequestBody AuthCompanyDTO authCompany) {
    try {
      var authenticatedCompany = this.authCompanyUseCase.execute(authCompany);

      return ResponseEntity.status(200).body(authenticatedCompany);
    } catch (Exception exception) {
      return ResponseEntity.status(401).body(exception.getMessage());
    }
  }
}
