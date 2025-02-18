package org.richard.vacancy_management.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.richard.vacancy_management.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUsername(String username);
}
