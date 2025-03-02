package org.richard.vacancy_management.security;

import java.io.IOException;

import org.richard.vacancy_management.providers.JWTCandidateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

  @Autowired
  private JWTCandidateProvider jwtCandidateProvider;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request, 
    HttpServletResponse response, 
    FilterChain filterChain
  ) throws ServletException, IOException {
    SecurityContextHolder.getContext().setAuthentication(null);

    String header = request.getHeader("Authorization");

    if (request.getRequestURI().startsWith("/candidate")) {
      if (header != null) {
        var subjectToken = this.jwtCandidateProvider.validateToken(header);
  
        if (subjectToken == null) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  
          return;
        }
  
        request.setAttribute("company_id", subjectToken);
      }
    }

    filterChain.doFilter(request, response);
  }
  
}
