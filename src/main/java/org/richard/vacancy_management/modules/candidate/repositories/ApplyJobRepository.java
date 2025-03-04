package org.richard.vacancy_management.modules.candidate.repositories;

import java.util.UUID;

import org.richard.vacancy_management.modules.candidate.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
