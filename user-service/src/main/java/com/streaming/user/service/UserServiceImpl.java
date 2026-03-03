package com.streaming.user.service;

import com.streaming.user.dto.UserRequest;
import com.streaming.user.dto.UserResponse;
import com.streaming.user.entity.User;
import com.streaming.user.exception.UserNotFoundException;
import com.streaming.user.mapper.UserMapper;
import com.streaming.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public UserResponse getById(Long id) {
    return userMapper.toResponse(findById(id));
  }

  @Override
  public UserResponse update(Long id, UserRequest request) {
    User user = findById(id);
    userMapper.updateEntity(request, user);
    return userMapper.toResponse(userRepository.save(user));
  }

  @Override
  public void delete(Long id) {
    findById(id);
    userRepository.deleteById(id);
  }

  @Override
  public UserResponse createFromAuth(Long id, String username, String email) {
    if (userRepository.existsById(id)) {
      return userMapper.toResponse(findById(id));
    }
    User user = User.builder()
      .id(id)
      .username(username)
      .email(email)
      .build();
    return userMapper.toResponse(userRepository.save(user));
  }

  private User findById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }
}
