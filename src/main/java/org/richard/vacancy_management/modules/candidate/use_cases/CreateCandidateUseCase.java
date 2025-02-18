package org.richard.vacancy_management.modules.candidate.use_cases;

import org.richard.vacancy_management.exceptions.CandidateNotFoundException;
import org.richard.vacancy_management.modules.candidate.CandidateEntity;
import org.richard.vacancy_management.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
  
  @Autowired
  private CandidateRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public CandidateEntity execute(CandidateEntity candidate) {
    this.repository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).ifPresent((user) -> {
      throw new CandidateNotFoundException();
    });

    var password = this.passwordEncoder.encode(candidate.getPassword());
    candidate.setPassword(password);

    var newCandidate = this.repository.save(candidate);

    return newCandidate;
  }
}
