package org.richard.vacancy_management.modules.candidate.controllers;

import java.util.UUID;

import org.richard.vacancy_management.modules.candidate.CandidateEntity;
import org.richard.vacancy_management.modules.candidate.use_cases.CreateCandidateUseCase;
import org.richard.vacancy_management.modules.candidate.use_cases.ProfileCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping
  public ResponseEntity<Object> craate(@Valid @RequestBody CandidateEntity candidate) {
    try {
      var newCandidate = this.createCandidateUseCase.execute(candidate);

      return ResponseEntity.status(201).body(newCandidate);
    } catch (Exception exception) {
      return ResponseEntity.status(400).body(exception.getMessage());
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var candidateId = request.getAttribute("candidate_id");

    try {
      var profileCandidate = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

      return ResponseEntity.ok().body(profileCandidate);
    } catch (Exception exception) {
      return ResponseEntity.status(400).body(exception.getMessage());
    }
  }
}
