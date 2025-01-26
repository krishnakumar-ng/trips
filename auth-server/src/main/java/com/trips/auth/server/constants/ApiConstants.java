package com.trips.auth.server.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstants {
    // API constants
    public static final String SCHEME = "https";
    public static final String HOSTNAME = "trips.com";
    public static final String API = "api";
    public static final String VERSION1 = "v1";

    // API URI
    public static final String AUTH_ENDPOINT =  "/auth";
    public static final String REGISTER_ENDPOINT =  "/register";
    public static final String TOKEN_ENDPOINT =  "/token";
    public static final String ROLES_ENDPOINT =  "/roles";

    //API Headers
    public static final String AUTHORIZATION =  "Authorization";

    // API error code
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String NOT_FOUND = "NOT_FOUND";


    public static final String MESSAGE_SUCCESS = "Success";
    public static final String MESSAGE_BAD_REQUEST = "Bad Request";
    public static final String MESSAGE_UNAUTHORIZED = "Unauthorized";
    public static final String MESSAGE_BUS_DATA = "Invalid bus data";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String MESSAGE_NOT_FOUND = "Not found";
    public static final String MESSAGE_SERVICE_UNAVAILABLE = "This service is currently unavailable";
}
