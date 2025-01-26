package com.trips.auth.server.data.mapper;

import com.trips.auth.server.data.entity.UserEntity;
import com.trips.auth.server.data.models.UserResponseModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseModel toUserResponseModel(UserEntity userEntity) {
        return UserResponseModel.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles())
                .build();
    }
}
