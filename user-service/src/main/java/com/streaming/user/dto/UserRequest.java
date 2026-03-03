package com.streaming.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
  @NotBlank
  private String username;
  @NotBlank @Email
  private String email;
  private String avatarUrl;
  private String bio;
}
