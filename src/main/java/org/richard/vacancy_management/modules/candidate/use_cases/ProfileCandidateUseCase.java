package org.richard.vacancy_management.modules.candidate.use_cases;

import java.util.UUID;

import org.richard.vacancy_management.exceptions.UserNotFoundException;
import org.richard.vacancy_management.modules.candidate.CandidateRepository;
import org.richard.vacancy_management.modules.candidate.dto.ProfileCandidateReponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileCandidateUseCase {
  
  @Autowired
  private CandidateRepository repository;

  public ProfileCandidateReponseDTO execute(UUID candidateId) {
    var candidate = this.repository.findById(candidateId).orElseThrow(() -> {
      throw new UserNotFoundException();
    });

    var candidateResponse = ProfileCandidateReponseDTO.builder()
      .id(candidate.getId())
      .name(candidate.getName())
      .username(candidate.getUsername())
      .email(candidate.getEmail())
      .description(candidate.getDescription())
      .build();

    return candidateResponse;
  }
}
