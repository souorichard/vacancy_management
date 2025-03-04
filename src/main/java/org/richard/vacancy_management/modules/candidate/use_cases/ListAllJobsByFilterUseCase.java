package org.richard.vacancy_management.modules.candidate.use_cases;

import java.util.List;

import org.richard.vacancy_management.modules.company.entities.JobEntity;
import org.richard.vacancy_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepository jobRepository;
  
  public List<JobEntity> execute(String filter) {
    return this.jobRepository.findByDescriptionContaining(filter);
  }
}
