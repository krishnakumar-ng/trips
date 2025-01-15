package com.trips.busservice.exceptions;

public class BusNotAvailableException extends RuntimeException {

    public BusNotAvailableException(String message) {
        super(message);
    }

    public BusNotAvailableException(String message, Throwable t) {
        super(message, t);
    }
}
