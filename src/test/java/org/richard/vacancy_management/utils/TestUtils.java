package org.richard.vacancy_management.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
  
  public static String objectToJSON(Object obj) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();

      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException exception) {
      throw new RuntimeException(exception);
    }
  }

  public static String generateToken(UUID companyId) {
    Algorithm algorithm = Algorithm.HMAC256("JACANCIES_@123$");

    var expiresAt = Instant.now().plus(Duration.ofMinutes(10));

    var token = JWT.create()
      .withIssuer("jacancies")
      .withSubject(companyId.toString())
      .withClaim("roles", Arrays.asList("COMPANY"))
      .withExpiresAt(expiresAt)
      .sign(algorithm);

    return token;
  }
}
