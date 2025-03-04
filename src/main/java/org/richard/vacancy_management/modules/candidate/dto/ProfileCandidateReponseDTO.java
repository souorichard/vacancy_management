package org.richard.vacancy_management.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
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

  @Schema(example = "Pedro Augusto")
  private String name;

  @Schema(example = "pedri_java")
  private String username;

  @Schema(example = "pedri@gmail.com")
  private String email;

  @Schema(example = "Java developer")
  private String description;
}
