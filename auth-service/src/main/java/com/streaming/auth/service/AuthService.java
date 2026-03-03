package com.streaming.auth.service;

import com.streaming.auth.dto.AuthResponse;
import com.streaming.auth.dto.LoginRequest;
import com.streaming.auth.dto.RegisterRequest;
import com.streaming.auth.entity.UserCredential;
import com.streaming.auth.exception.AuthException;
import com.streaming.auth.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserCredentialRepository userCredentialRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthResponse register(RegisterRequest request) {
    if (userCredentialRepository.existsByEmail(request.getEmail())) {
      throw new AuthException("Email already exists");
    }
    if (userCredentialRepository.existsByUsername(request.getUsername())) {
      throw new AuthException("Username already exists");
    }

    UserCredential user = UserCredential.builder()
      .username(request.getUsername())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(UserCredential.Role.USER)
      .build();

    UserCredential saved = userCredentialRepository.save(user);

    String token = jwtService.generateToken(
      saved.getEmail(),
      saved.getRole().name(),
      saved.getId()
    );

    return AuthResponse.builder()
      .token(token)
      .username(saved.getUsername())
      .email(saved.getEmail())
      .role(saved.getRole().name())
      .userId(saved.getId())
      .build();
  }

  public AuthResponse login(LoginRequest request) {
    UserCredential user = userCredentialRepository.findByEmail(request.getEmail())
      .orElseThrow(() -> new AuthException("Invalid email or password"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new AuthException("Invalid email or password");
    }

    String token = jwtService.generateToken(
      user.getEmail(),
      user.getRole().name(),
      user.getId()
    );

    return AuthResponse.builder()
      .token(token)
      .username(user.getUsername())
      .email(user.getEmail())
      .role(user.getRole().name())
      .userId(user.getId())
      .build();
  }

  public boolean validateToken(String token) {
    return jwtService.validateToken(token);
  }
}
