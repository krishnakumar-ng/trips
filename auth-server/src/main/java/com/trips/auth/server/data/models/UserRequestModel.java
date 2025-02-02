package com.trips.auth.server.data.models;

import com.trips.auth.server.constants.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {
    private String id;
    private String username;
    private String password;
    private Set<Role> roles;
}
