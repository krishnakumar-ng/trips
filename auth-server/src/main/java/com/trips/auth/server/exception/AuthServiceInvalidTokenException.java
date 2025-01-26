package com.trips.auth.server.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"stackTrace","cause","message", "suppressed", "localizedMessage"})
public class AuthServiceInvalidTokenException extends RuntimeException{
    public final HttpStatus errorCode;
    public final String errorMessage;

    public AuthServiceInvalidTokenException(HttpStatus errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
