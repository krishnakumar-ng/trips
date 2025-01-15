package com.trips.paymentservice.exception;

import lombok.Data;

@Data
public class PaymentServiceWebException extends RuntimeException{
    private final String errorCode;
    private final String message;
    private final String details;

    public PaymentServiceWebException(String errorCode, String message, String details){
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }
}
