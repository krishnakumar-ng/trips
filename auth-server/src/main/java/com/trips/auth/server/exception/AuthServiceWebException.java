package com.trips.auth.server.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"stackTrace","cause","message", "suppressed", "localizedMessage"})
public class AuthServiceWebException extends RuntimeException {
    private final int statusCode;
    private final String errorMessage;

    public AuthServiceWebException(int statusCode, String errorMessage, Throwable err){
        super(errorMessage, err);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}
