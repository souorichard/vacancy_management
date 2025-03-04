package org.richard.vacancy_management.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.richard.vacancy_management.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

  List<JobEntity> findByDescriptionContaining(String filter);
}
