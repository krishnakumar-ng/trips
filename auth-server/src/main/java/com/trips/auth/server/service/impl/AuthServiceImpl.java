package com.trips.auth.server.service.impl;

import com.trips.auth.server.constants.enums.AuthStatus;
import com.trips.auth.server.constants.enums.Role;
import com.trips.auth.server.data.entity.UserEntity;
import com.trips.auth.server.data.mapper.UserMapper;
import com.trips.auth.server.data.models.TokenResponseModel;
import com.trips.auth.server.data.models.UserRequestModel;
import com.trips.auth.server.data.models.UserResponseModel;
import com.trips.auth.server.data.models.VerifyTokenResponseModel;
import com.trips.auth.server.exception.UserNameIsAlreadyTakenException;
import com.trips.auth.server.repository.UserRepository;
import com.trips.auth.server.service.AuthService;
import com.trips.auth.server.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

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

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(userRequestModel.getUsername());

        String encodePassword = passwordEncoder.encode(userRequestModel.getPassword());
        newUserEntity.setPassword(encodePassword);

        //convert role names to role entities and assign to user
        Set<Role> roles = userRequestModel.getRoles();

        newUserEntity.setRoles(roles);
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return userMapper.toUserResponseModel(savedUserEntity);
    }

    @Override
    public TokenResponseModel getAccessToken(UserEntity userEntity) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        String tokenString = jwtService.generateToken(userEntity.getUsername());
        return new TokenResponseModel(tokenString, jwtService.getExpirationTime(), "Bearer");
    }

    @Override
    public VerifyTokenResponseModel verifyToken(HttpServletRequest httpServletRequest) {

            jwtService.verifyToken(httpServletRequest);
            VerifyTokenResponseModel verifyTokenResponse = new VerifyTokenResponseModel();
            verifyTokenResponse.setStatus(AuthStatus.SUCCESS);
            verifyTokenResponse.setMessage("Token successfully verified");
            return verifyTokenResponse;

    }
}
