package com.trips.auth.server.service.impl;

import com.trips.auth.server.constants.enums.Role;
import com.trips.auth.server.data.entity.UserEntity;
import com.trips.auth.server.data.mapper.UserMapper;
import com.trips.auth.server.data.models.UserResponseModel;
import com.trips.auth.server.repository.UserRepository;
import com.trips.auth.server.service.JwtService;
import com.trips.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseModel getUserByName(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            return userMapper.toUserResponseModel(userEntity);
        } else {
            throw new UsernameNotFoundException("User don't exist");
        }

    }

    @Override
    public UserResponseModel updateUserPassword(String username, String password) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            userEntity.setPassword(password);
            UserEntity savedUserEntity = userRepository.save(userEntity);
            return userMapper.toUserResponseModel(savedUserEntity);
        } else {
            throw new UsernameNotFoundException("User don't exist");
        }
    }

    @Override
    public UserResponseModel updateUserRoles(String username, String rolesString) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            Set<Role> roles = Arrays.stream(rolesString.split(","))
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            userEntity.setRoles(roles);
            UserEntity savedUserEntity = userRepository.save(userEntity);
            return userMapper.toUserResponseModel(savedUserEntity);
        } else {
            throw new UsernameNotFoundException("User don't exist");
        }
    }
}
