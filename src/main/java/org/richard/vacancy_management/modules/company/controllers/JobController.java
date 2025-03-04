package org.richard.vacancy_management.modules.company.controllers;

import java.util.UUID;

import org.richard.vacancy_management.modules.company.dto.CreateJobDTO;
import org.richard.vacancy_management.modules.company.entities.JobEntity;
import org.richard.vacancy_management.modules.company.use_cases.CreateJobUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
@Tag(name = "Vacancies", description = "Vacancies informations")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping
  @PreAuthorize("hasRole('COMPANY')")
  @Operation(summary = "Register vacancies", description = "This function is responsible for registering vacancies in the company")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = JobEntity.class))
    })
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    try {
      var jobEntity = JobEntity.builder()
        .description(createJobDTO.getDescription())
        .benefits(createJobDTO.getBenefits())
        .level(createJobDTO.getLevel())
        .companyId(UUID.fromString(companyId.toString()))
        .build();

      var newJob = this.createJobUseCase.execute(jobEntity);

      return ResponseEntity.status(201).body(newJob);
    } catch (Exception exception) {
      return ResponseEntity.status(400).body(exception.getMessage());
    }
  }
}
