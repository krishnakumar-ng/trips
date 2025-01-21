package com.trips.auth.server.data.mapper;

import com.trips.auth.server.data.entity.User;
import com.trips.auth.server.data.models.UserResponseModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserResponseModel toUserResponseModel(User user) {
        return UserResponseModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
