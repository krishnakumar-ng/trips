package com.trips.auth.server.api.controller;

import com.trips.auth.server.api.resource.AuthResource;
import com.trips.auth.server.data.entity.UserEntity;
import com.trips.auth.server.data.models.TokenResponseModel;
import com.trips.auth.server.data.models.UserRequestModel;
import com.trips.auth.server.data.models.UserResponseModel;
import com.trips.auth.server.data.models.VerifyTokenResponseModel;
import com.trips.auth.server.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthResource {
    private final AuthService authService;

    @Override
    public ResponseEntity<TokenResponseModel> getAccessToken(UserEntity userEntity) {
        TokenResponseModel tokenResponseModel = authService.getAccessToken(userEntity);
        return new ResponseEntity<>(tokenResponseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VerifyTokenResponseModel> verifyToken(HttpServletRequest httpServletRequest) {
        VerifyTokenResponseModel verifyTokenResponseModel = authService.verifyToken(httpServletRequest);
        return new ResponseEntity<>(verifyTokenResponseModel,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseModel> register(UserRequestModel userRequestModel) {
        return new ResponseEntity<>(authService.register(userRequestModel),HttpStatus.OK);
    }
}
