package com.app.authservice.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.authservice.dto.AuthResponse;
import com.app.authservice.dto.LoginRequest;
import com.app.authservice.dto.RegisterRequest;
import com.app.authservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ✅ REGISTER API
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);

            return ResponseEntity.ok(
                Map.of("message", "User registered successfully")
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(
                Map.of("message", e.getMessage())
            );
        }
    }

    // ✅ LOGIN API (RETURNS TOKEN)
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        try {
//            String token = authService.login(request);
//
//            return ResponseEntity.ok(
//                new AuthResponse(token)
//            );
//
//        } catch (RuntimeException e) {
//
//            return ResponseEntity.badRequest().body(
//                Map.of("message", e.getMessage())
//            );
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // ✅ get user
            var user = authService.login(request);

            // ✅ generate token
            String token = authService.generateToken(user.getEmail());

            // ✅ return both
            return ResponseEntity.ok(
                new AuthResponse(token, user.getName())
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(
                Map.of("message", e.getMessage())
            );
        }
    }
}