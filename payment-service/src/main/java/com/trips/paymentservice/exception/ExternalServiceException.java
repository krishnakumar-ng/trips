package com.trips.paymentservice.exception;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends RuntimeException {
    private Integer errorCode;
    private HttpStatus errorStatus;

    public ExternalServiceException(Integer errorCode, HttpStatus errorStatus, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
    }
}
