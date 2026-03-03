package com.streaming.user.mapper;

import com.streaming.user.dto.UserRequest;
import com.streaming.user.dto.UserResponse;
import com.streaming.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toEntity(UserRequest request);
  UserResponse toResponse(User user);
  void updateEntity(UserRequest request, @MappingTarget User user);
}
