package com.trips.auth.server.exception;

import com.trips.auth.server.constants.ApiConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class AuthServiceExceptionHandler {
    @ExceptionHandler({AuthServiceInvalidTokenException.class})
    public ResponseEntity<AuthServiceInvalidTokenException> handleFirebaseAuthException(AuthServiceInvalidTokenException ex, WebRequest request) {
        log.error(ApiConstants.UNAUTHORIZED, ex);
        return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<AuthServiceWebException> handleException(Exception ex, WebRequest request) {
        log.error(ApiConstants.INTERNAL_SERVER_ERROR, ex);
        return new ResponseEntity<>(new AuthServiceWebException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
