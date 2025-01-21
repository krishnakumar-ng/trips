package com.trips.auth.server.service.impl;

import com.trips.auth.server.constants.Role;
import com.trips.auth.server.data.entity.User;
import com.trips.auth.server.data.mapper.UserMapper;
import com.trips.auth.server.data.models.TokenResponseModel;
import com.trips.auth.server.data.models.UserRequestModel;
import com.trips.auth.server.data.models.UserResponseModel;
import com.trips.auth.server.exception.UserNameIsAlreadyTakenException;
import com.trips.auth.server.repository.UserRepository;
import com.trips.auth.server.service.AuthService;
import com.trips.auth.server.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseModel register(UserRequestModel userRequestModel) {
        if (userRepository.findByUsername(userRequestModel.getUsername()).isPresent()) {
            throw new UserNameIsAlreadyTakenException(userRequestModel.getUsername() + " is already taken. Use different user name.");
        }

        User newUser = new User();
        newUser.setUsername(userRequestModel.getUsername());

        String encodePassword = passwordEncoder.encode(userRequestModel.getPassword());
        newUser.setPassword(encodePassword);

        //convert role names to role entities and assign to user
        Set<Role> roles = userRequestModel.getRoles();

        newUser.setRoles(roles);
        User savedUser = userRepository.save(newUser);
        return userMapper.toUserResponseModel(savedUser);
    }

    @Override
    public TokenResponseModel login(User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        String tokenString = jwtService.generateToken(user.getUsername());
        return new TokenResponseModel(tokenString, jwtService.getExpirationTime(), "Bearer");
    }
}
