package com.trips.paymentservice.exception;

public class PaymentServiceRuntimeException extends RuntimeException{
    public PaymentServiceRuntimeException(String message){
        super(message);
    }
}
