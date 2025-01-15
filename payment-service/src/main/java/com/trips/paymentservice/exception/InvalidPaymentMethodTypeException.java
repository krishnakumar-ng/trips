package com.trips.paymentservice.exception;

public class InvalidPaymentMethodTypeException extends RuntimeException{
    public InvalidPaymentMethodTypeException(String message){
        super(message);
    }
}
