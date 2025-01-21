package com.trips.auth.server.api.controller;

import com.trips.auth.server.api.resource.UserResource;
import com.trips.auth.server.data.models.UserResponseModel;
import com.trips.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController implements UserResource {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseModel> getUserDetails(String token, String userName) {
        UserResponseModel user = userService.getUserByName(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseModel> updateUserPassword(String token, String username, String password) {
        UserResponseModel user = userService.updateUserPassword(username,password);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseModel> updateUserRoles(String token, String userName, String roles) {
        UserResponseModel user = userService.updateUserRoles(userName,roles);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
