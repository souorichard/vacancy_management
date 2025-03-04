package org.richard.vacancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class CreateJobDTO {
  
  @Schema(example = "Junior developer vacancy", requiredMode = RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "GYM Pass, Plano de Saúde", requiredMode = RequiredMode.REQUIRED)
  private String benefits;

  @Schema(example = "JUNIOR", requiredMode = RequiredMode.REQUIRED)
  private String level;
}
