package com.trips.auth.server.service.impl;

import com.trips.auth.server.constants.Role;
import com.trips.auth.server.data.entity.User;
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
    public UserResponseModel getUserByName(String userName) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.toUserResponseModel(user);
        } else {
            throw new UsernameNotFoundException("User don't exist");
        }

    }

    @Override
    public UserResponseModel updateUserPassword(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(password);
            User savedUser = userRepository.save(user);
            return userMapper.toUserResponseModel(savedUser);
        } else {
            throw new UsernameNotFoundException("User don't exist");
        }
    }

    @Override
    public UserResponseModel updateUserRoles(String userName, String rolesString) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<Role> roles = Arrays.stream(rolesString.split(","))
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
            User savedUser = userRepository.save(user);
            return userMapper.toUserResponseModel(savedUser);
        } else {
            throw new UsernameNotFoundException("User don't exist");
        }
    }
}
