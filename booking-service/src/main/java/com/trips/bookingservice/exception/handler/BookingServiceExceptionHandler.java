package com.trips.bookingservice.exception.handler;

import com.trips.bookingservice.constants.ApiConstants;
import com.trips.bookingservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class BookingServiceExceptionHandler {
    @ExceptionHandler({PaymentServiceException.class})
    public ResponseEntity<BookingServiceWebException> handlePaymentServiceException(PaymentServiceException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidBookingRequestDataException.class})
    public ResponseEntity<BookingServiceWebException> handleInvalidBookingRequestDataException(InvalidBookingRequestDataException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPaymentMethodTypeException.class})
    public ResponseEntity<BookingServiceWebException> handleInvalidPaymentMethodTypeException(InvalidPaymentMethodTypeException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPaymentTypeException.class})
    public ResponseEntity<BookingServiceWebException> handleInvalidPaymentTypeException(InvalidPaymentTypeException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BusUnavailableException.class})
    public ResponseEntity<BookingServiceWebException> handleBusUnavailableException(BusUnavailableException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookingNotFoundException.class,})
    public ResponseEntity<BookingServiceWebException> handleBookingNotFoundException(BookingNotFoundException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookingRequestUnauthorizedAccessException.class})
    public ResponseEntity<BookingServiceWebException> handleBookingRequestUnauthorizedAccessException(BookingRequestUnauthorizedAccessException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BusServiceException.class})
    public ResponseEntity<BookingServiceWebException> handleRoomServiceException(BusServiceException exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(exception.getErrorCode(), exception.getMessage(), exception.getDetails()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, BookingServiceRuntimeException.class})
    public ResponseEntity<BookingServiceWebException> handleException(Exception exception, WebRequest request) {
        log.error("Error: {}", exception.getMessage());
        return new ResponseEntity<>(new BookingServiceWebException(ApiConstants.INTERNAL_SERVER_ERROR, ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR, ""), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}