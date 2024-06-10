package com.github.alfonsoleandro.pencaucu.business.jwt.impl;

import com.github.alfonsoleandro.pencaucu.business.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.expiration-time-in-days}")
    private int expiration;

    private final String secretKey;


    public JwtServiceImpl() throws IOException {
        this.secretKey = Files.readString(Path.of(System.getenv("JWT_SECRET_PATH")));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails subject) {
        return this.generateToken(Map.of(), subject);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails subject) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(subject.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (long) this.expiration *1000*60*60*24))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration != null && expiration.before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .decryptWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
