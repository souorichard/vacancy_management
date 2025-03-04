package org.richard.vacancy_management.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.richard.vacancy_management.modules.candidate.CandidateEntity;
import org.richard.vacancy_management.modules.candidate.use_cases.CreateCandidateUseCase;
import org.richard.vacancy_management.modules.candidate.use_cases.ListAllJobsByFilterUseCase;
import org.richard.vacancy_management.modules.candidate.use_cases.ProfileCandidateUseCase;
import org.richard.vacancy_management.modules.company.entities.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

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

  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Tag(name = "Candidate", description = "Candidate informations")
  @Operation(summary = "List of vacancies available for the candidate", description = "This function is responsible for listing all available vacancies, based onn the filter")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(
        schema = @Schema(implementation = JobEntity.class)
      ))
    })
  })
  public ResponseEntity<List<JobEntity>> findJobByFilter(@RequestParam String filter) {
    try {
      var jobs = this.listAllJobsByFilterUseCase.execute(filter);

      return ResponseEntity.ok().body(jobs);
    } catch (Exception exception) {
      return null;
    }
  }
}
