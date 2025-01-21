package com.trips.auth.server.service;

import com.trips.auth.server.data.models.UserResponseModel;

import java.util.List;

public interface UserService {
    UserResponseModel getUserByName(String userName);

    UserResponseModel updateUserPassword(String username, String password);

    UserResponseModel updateUserRoles(String userName, String roles);
}
