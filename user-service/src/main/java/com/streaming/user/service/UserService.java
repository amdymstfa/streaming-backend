package com.streaming.user.service;

import com.streaming.user.dto.UserRequest;
import com.streaming.user.dto.UserResponse;

public interface UserService {
  UserResponse getById(Long id);
  UserResponse update(Long id, UserRequest request);
  void delete(Long id);
  UserResponse createFromAuth(Long id, String username, String email);
}
