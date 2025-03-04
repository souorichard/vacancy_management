package org.richard.vacancy_management.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Pedro Augusto", requiredMode = RequiredMode.REQUIRED, description = "Cadidate name")
  private String name;

  @Pattern(regexp = "^\\S+", message = "O campo [username] não pode conter espaços em branco")
  @Schema(example = "pedri_java", requiredMode = RequiredMode.REQUIRED, description = "Cadidate username")
  private String username;

  @Email(message = "O campo [e-mai] deve conter um e-mail válido")
  @Schema(example = "pedri@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "Cadidate email")
  private String email;

  @Length(min = 8, max = 100, message = "O campo [senha] deve conter no mínimo 8 caracteres")
  @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Cadidate password")
  private String password;

  @Schema(example = "Java Developer", requiredMode = RequiredMode.REQUIRED, description = "Cadidate description")
  private String description;
  private String curriculum;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
