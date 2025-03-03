package org.richard.vacancy_management.modules.company.use_cases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.richard.vacancy_management.modules.company.dto.AuthCompanyRequestDTO;
import org.richard.vacancy_management.modules.company.dto.AuthCompanyResponseDTO;
import org.richard.vacancy_management.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCompanyResponseDTO execute(AuthCompanyRequestDTO authCompany) throws AuthenticationException {
    var company = this.repository.findByUsername(authCompany.getUsername()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Username or password is incorrect");
    });

    var passwordMatches = this.passwordEncoder.matches(authCompany.getPassword(), company.getPassword());

    if (!passwordMatches) throw new AuthenticationException();

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresAt = Instant.now().plus(Duration.ofMinutes(10));

    var token = JWT.create()
      .withIssuer("jacancies")
      .withSubject(company.getId().toString())
      .withClaim("roles", Arrays.asList("COMPANY"))
      .withExpiresAt(expiresAt)
      .sign(algorithm);

    var authCompanyResponse = AuthCompanyResponseDTO.builder()
      .access_token(token)
      .expires_at(expiresAt.toEpochMilli())
      .build();

    return authCompanyResponse;
  }
}
