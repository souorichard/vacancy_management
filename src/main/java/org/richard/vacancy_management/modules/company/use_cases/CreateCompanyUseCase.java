package org.richard.vacancy_management.modules.company.use_cases;

import org.richard.vacancy_management.exceptions.CompanyNotFoundException;
import org.richard.vacancy_management.modules.company.entities.CompanyEntity;
import org.richard.vacancy_management.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public CompanyEntity execute(CompanyEntity company) {
    this.repository.findByUsername(company.getUsername()).ifPresent((user) -> {
      throw new CompanyNotFoundException();
    });

    var password = this.passwordEncoder.encode(company.getPassword());

    company.setPassword(password);

    var newCompany = this.repository.save(company);

    return newCompany;
  }
}
