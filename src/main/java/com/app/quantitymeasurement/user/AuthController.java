package com.app.quantitymeasurement.user;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER API
    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
    	authService.register(request);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    // LOGIN API (RETURNS TOKEN)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return new AuthResponse(token);
    }
}