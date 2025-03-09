package org.richard.vacancy_management.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Pattern(regexp = "^\\S+", message = "O campo [username] não pode conter espaços em branco")
  private String username;

  @Email(message = "O campo [e-mai] deve conter um e-mail válido")
  private String email;

  @Length(min = 8, max = 100, message = "O campo [senha] deve conter no mínimo 8 caracteres")
  private String password;
  
  private String website;
  private String description;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
