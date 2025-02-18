package org.richard.vacancy_management.modules.company.use_cases;

import org.richard.vacancy_management.modules.company.entities.JobEntity;
import org.richard.vacancy_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateJobUseCase {

  @Autowired
  private JobRepository repository;
  
  public JobEntity execute(JobEntity job) {
    var newJob = this.repository.save(job);

    return newJob;
  }
}
