package org.richard.vacancy_management.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.richard.vacancy_management.modules.company.entities.JobEntity;

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
@Entity(name = "apply_jobs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyJobEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
  private CandidateEntity candidateEntity;

  @Column(name = "candidate_id")
  private UUID candidateId;

  @ManyToOne
  @JoinColumn(name = "job_id", insertable = false, updatable = false)
  private JobEntity jobEntity;

  @Column(name = "job_id")
  private UUID jobId;
  
  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
