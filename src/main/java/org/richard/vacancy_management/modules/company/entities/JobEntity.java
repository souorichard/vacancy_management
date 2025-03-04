package org.richard.vacancy_management.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "jobs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Design vacancy", requiredMode = RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "SENIOR", requiredMode = RequiredMode.REQUIRED)
  private String level;

  @Schema(example = "GYM Pass, Plano de Saúde", requiredMode = RequiredMode.REQUIRED)
  private String benefits;

  @Column(name = "company_id")
  private UUID companyId;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity companyEntity;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
