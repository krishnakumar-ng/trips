package com.trips.auth.server.service;

import com.trips.auth.server.data.models.UserResponseModel;

public interface UserService {
    UserResponseModel getUserByName(String username);

    UserResponseModel updateUserPassword(String username, String password);

    UserResponseModel updateUserRoles(String username, String roles);
}
