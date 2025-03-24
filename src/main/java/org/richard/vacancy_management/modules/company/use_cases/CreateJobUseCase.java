package org.richard.vacancy_management.modules.company.use_cases;

import org.richard.vacancy_management.exceptions.CompanyNotFoundException;
import org.richard.vacancy_management.modules.company.entities.JobEntity;
import org.richard.vacancy_management.modules.company.repositories.CompanyRepository;
import org.richard.vacancy_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository repository;

  @Autowired
  private CompanyRepository companyRepository;
  
  public JobEntity execute(JobEntity job) {
    companyRepository.findById(job.getCompanyId()).orElseThrow(() -> {
      throw new CompanyNotFoundException();
    });

    var newJob = this.repository.save(job);

    return newJob;
  }
}
