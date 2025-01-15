package com.trips.bookingservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookingServiceWebException extends RuntimeException{
    private final String errorCode;
    private final String message;
    private final String details;

    public BookingServiceWebException(String errorCode, String message, String details){
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }
}
