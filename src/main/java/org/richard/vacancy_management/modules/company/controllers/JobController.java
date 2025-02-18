package org.richard.vacancy_management.modules.company.controllers;

import java.util.UUID;

import org.richard.vacancy_management.modules.company.dto.CreateJobDTO;
import org.richard.vacancy_management.modules.company.entities.JobEntity;
import org.richard.vacancy_management.modules.company.use_cases.CreateJobUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping
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
