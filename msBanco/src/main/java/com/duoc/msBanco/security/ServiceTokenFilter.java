package com.duoc.msBanco.security;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class ServiceTokenFilter extends OncePerRequestFilter {

  private final ServiceTokenUtil serviceTokenUtil;

  public ServiceTokenFilter(ServiceTokenUtil serviceTokenUtil) {
    this.serviceTokenUtil = serviceTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);

      try {
        Claims claims = serviceTokenUtil.parseToken(token);

        // Verificar datos del token (se imprimen en consola)
        System.out.println("Username: " + claims.get("username"));
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Tipo: " + claims.get("tipo"));

        request.setAttribute("claims", claims);
      } catch (Exception e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        return;
      }
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header missing or invalid");
      return;
    }

    filterChain.doFilter(request, response);

  }



}
