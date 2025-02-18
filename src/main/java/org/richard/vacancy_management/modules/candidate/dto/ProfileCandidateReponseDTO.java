package org.richard.vacancy_management.modules.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateReponseDTO {
  
  private UUID id;
  private String name;
  private String username;
  private String email;
  private String description;
}
