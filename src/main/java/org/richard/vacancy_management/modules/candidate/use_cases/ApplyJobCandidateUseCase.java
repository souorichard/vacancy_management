package org.richard.vacancy_management.modules.candidate.use_cases;

import java.util.UUID;

import org.richard.vacancy_management.exceptions.CandidateNotFoundException;
import org.richard.vacancy_management.exceptions.JobNotFoundException;
import org.richard.vacancy_management.modules.candidate.CandidateRepository;
import org.richard.vacancy_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;
  
  public void execute(UUID candidateId, UUID jobId) {
    this.candidateRepository.findById(candidateId).orElseThrow(() -> {
      throw new CandidateNotFoundException();
    });

    this.jobRepository.findById(jobId).orElseThrow(() -> {
      throw new JobNotFoundException();
    });
  }
}
