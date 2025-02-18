package org.richard.vacancy_management.modules.company.controllers;

import org.richard.vacancy_management.modules.company.entities.CompanyEntity;
import org.richard.vacancy_management.modules.company.use_cases.CreateCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
  
  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
    try {
      var newCompany = this.createCompanyUseCase.execute(company);

      return ResponseEntity.status(201).body(newCompany);
    } catch (Exception exception) {
      return ResponseEntity.status(400).body(exception.getMessage());
    }
  }
}
