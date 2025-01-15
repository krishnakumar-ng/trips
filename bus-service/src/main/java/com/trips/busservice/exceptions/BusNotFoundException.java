package com.trips.busservice.exceptions;

public class BusNotFoundException extends RuntimeException {
    public BusNotFoundException(String message) {
        super(message);
    }

    public BusNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
