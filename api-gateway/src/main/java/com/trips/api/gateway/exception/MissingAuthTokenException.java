package com.trips.api.gateway.exception;

public class MissingAuthTokenException extends RuntimeException{
    public MissingAuthTokenException(String message){
        super(message);
    }
}
