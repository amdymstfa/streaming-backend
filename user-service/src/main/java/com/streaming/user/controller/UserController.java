package com.streaming.user.controller;

import com.streaming.user.dto.UserRequest;
import com.streaming.user.dto.UserResponse;
import com.streaming.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                             @Valid @RequestBody UserRequest request) {
    return ResponseEntity.ok(userService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/sync")
  public ResponseEntity<UserResponse> syncFromAuth(
    @RequestParam Long id,
    @RequestParam String username,
    @RequestParam String email) {
    return ResponseEntity.ok(userService.createFromAuth(id, username, email));
  }
}
