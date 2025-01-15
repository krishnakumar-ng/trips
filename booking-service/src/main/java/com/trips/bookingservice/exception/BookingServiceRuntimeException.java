package com.trips.bookingservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookingServiceRuntimeException extends RuntimeException{
    public BookingServiceRuntimeException(String message){
        super(message);
    }
}
