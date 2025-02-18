package org.richard.vacancy_management.exceptions;

public class CandidateNotFoundException extends RuntimeException {
  public CandidateNotFoundException() {
    super("Candidato já cadastrado");
  }
}
