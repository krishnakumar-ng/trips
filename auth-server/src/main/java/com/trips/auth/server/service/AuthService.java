package com.trips.auth.server.service;

import com.trips.auth.server.data.entity.UserEntity;
import com.trips.auth.server.data.models.TokenResponseModel;
import com.trips.auth.server.data.models.UserRequestModel;
import com.trips.auth.server.data.models.UserResponseModel;
import com.trips.auth.server.data.models.VerifyTokenResponseModel;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    UserResponseModel register(UserRequestModel userRequestModel);

    TokenResponseModel getAccessToken(UserEntity userEntity);

    VerifyTokenResponseModel verifyToken(HttpServletRequest httpServletRequest);
}
