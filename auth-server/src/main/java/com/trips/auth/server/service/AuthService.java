package com.trips.auth.server.service;

import com.trips.auth.server.data.entity.User;
import com.trips.auth.server.data.models.TokenResponseModel;
import com.trips.auth.server.data.models.UserRequestModel;
import com.trips.auth.server.data.models.UserResponseModel;

public interface AuthService {
    UserResponseModel register(UserRequestModel userRequestModel);
    TokenResponseModel login(User user);
}
