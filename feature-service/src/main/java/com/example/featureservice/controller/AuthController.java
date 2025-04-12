package com.example.featureservice.controller;

import com.example.featureservice.dto.AuthRequest;
import com.example.featureservice.dto.AuthResponse;
import com.example.featureservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        String jwtToken = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
}

