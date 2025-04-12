package com.example.featureservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private Long jwtExpirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Sign with the secret key
                .compact();

    }

    // Method to validate the token (e.g., expiration, signature)
    public boolean validateToken(String token) {
        try {
            // Parse the token to check its validity
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token); //

            return true;
//        } catch (SignatureException ex) {
//            System.out.println("Invalid JWT signature.");
        } catch (io.jsonwebtoken.ExpiredJwtException ex) {
            System.out.println("Expired JWT token.");
        } catch (io.jsonwebtoken.MalformedJwtException ex) {
            System.out.println("Invalid JWT token.");
        } catch (Exception ex) {
            System.out.println("JWT validation failed: " + ex.getMessage());
        }
        return false; // If validation fails, return false
    }

    // Extract username (subject) from the token
    public String getUsernameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token); // Extract claims
        return claims.getSubject(); // Subject is the username
    }

    // Extract the role from the token
    public String getRoleFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token); // Extract claims
        return claims.get("role", String.class); // Retrieve the "role" claim
    }

    // Helper method to extract all claims from the token
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody(); // Get the JWT claims
    }

}

