package com.streaming.auth.controller;

import com.streaming.auth.dto.AuthResponse;
import com.streaming.auth.dto.LoginRequest;
import com.streaming.auth.dto.RegisterRequest;
import com.streaming.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @GetMapping("/validate")
  public ResponseEntity<Map<String, Boolean>> validateToken(@RequestParam String token) {
    boolean isValid = authService.validateToken(token);
    return ResponseEntity.ok(Map.of("valid", isValid));
  }
}
