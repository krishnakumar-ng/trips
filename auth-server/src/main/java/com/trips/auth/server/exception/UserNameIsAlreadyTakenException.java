package com.trips.auth.server.exception;

public class UserNameIsAlreadyTakenException extends RuntimeException {
    public UserNameIsAlreadyTakenException(String message) {
        super(message);
    }
}
