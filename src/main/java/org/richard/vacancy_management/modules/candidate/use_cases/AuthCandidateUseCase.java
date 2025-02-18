package org.richard.vacancy_management.modules.candidate.use_cases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.richard.vacancy_management.modules.candidate.CandidateRepository;
import org.richard.vacancy_management.modules.candidate.dto.AuthCandidateRequestDTO;
import org.richard.vacancy_management.modules.candidate.dto.AuthCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private CandidateRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
    var candidate = this.repository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Username/Password incorrect");
    });

    var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) throw new AuthenticationException();

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresAt = Instant.now().plus(Duration.ofMinutes(10));

    var token = JWT.create()
      .withIssuer("jacancies")
      .withSubject(candidate.getId().toString())
      .withClaim("roles", Arrays.asList("candidate"))
      .withExpiresAt(expiresAt)
      .sign(algorithm);

    var authCandidateResponse = AuthCandidateResponseDTO.builder()
      .access_token(token)
      .expires_at(expiresAt.toEpochMilli())
      .build();

    return authCandidateResponse;
  }
}
