package org.richard.vacancy_management.exceptions;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException() {
    super("Compania já cadastrada");
  }
}
