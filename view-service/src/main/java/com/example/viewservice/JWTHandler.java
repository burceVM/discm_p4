package com.example.viewservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

public class JWTHandler {
    private static Algorithm algorithm;
    private static JWTVerifier verifier;
    public JWTHandler() {
         algorithm = Algorithm.HMAC256("lozol");
         verifier = JWT.require(algorithm).withIssuer("lozol").build();
    }

    public String returnJWTStringForStudent(String name)
    {
        return JWT.create()
                .withIssuer("lozol")
                .withSubject(name)
                .withClaim("role", "student")
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(algorithm);
    }

    public String returnJWTStringForFaculty(String name)
    {
        return JWT.create()
                .withIssuer("lozol")
                .withSubject(name)
                .withClaim("role", "faculty")
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(algorithm);
    }

    public String returnRole(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            Claim claim = decodedJWT.getClaim("role");
            String role = claim.asString();
            return role;
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

}
