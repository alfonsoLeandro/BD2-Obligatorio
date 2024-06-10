package com.github.alfonsoleandro.pencaucu.business.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
public interface JwtService {

    String extractUsername(String token);

    String generateToken(UserDetails subject);

    String generateToken(Map<String, Object> extraClaims, UserDetails subject);

    boolean isTokenValid(String token, UserDetails userDetails);
}
