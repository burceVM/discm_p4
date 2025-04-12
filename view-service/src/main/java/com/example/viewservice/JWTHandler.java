package com.example.viewservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.UUID;

public class JWTHandler {
    private static Algorithm algorithm;
    private static JWTVerifier verifier;
    public JWTHandler() {
         algorithm = Algorithm.HMAC256("lozol");
         verifier = JWT.require(algorithm).withIssuer("lozol").build();
    }

    public static String returnJWTStringForStudent(String name)
    {
        return JWT.create()
                .withIssuer("lozol")
                .withSubject(name)
                .withClaim("username", name)
                .withClaim("role", "student")
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(algorithm);
    }

    public static String returnJWTStringForFaculty(String name)
    {
        return JWT.create()
                .withIssuer("lozol")
                .withSubject(name)
                .withClaim("username", name)
                .withClaim("role", "faculty")
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(algorithm);
    }

    public static String returnRole(HttpServletRequest request) {
        try {
            Cookie cookie = getCookie(request);
            DecodedJWT decodedJWT = verifier.verify(cookie.getValue());
            Claim claim = decodedJWT.getClaim("role");
            String role = claim.asString();
            return role;
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    public static String returnUsername(HttpServletRequest request) {
        try {
            Cookie cookie = getCookie(request);
            DecodedJWT decodedJWT = verifier.verify(cookie.getValue());
            Claim claim = decodedJWT.getClaim("username");
            String role = claim.asString();
            return role;
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
