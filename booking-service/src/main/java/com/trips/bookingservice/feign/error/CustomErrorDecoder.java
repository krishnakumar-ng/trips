package com.trips.bookingservice.feign.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trips.bookingservice.data.model.ErrorMessage;
import com.trips.bookingservice.exception.ExternalServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            log.info("input: {}", response.body().asInputStream());
            ErrorMessage errorMessage = objectMapper.readValue(response.body().asInputStream(), ErrorMessage.class);
            return new ExternalServiceException(errorMessage.getErrorCode().toString(), errorMessage.getErrorStatus().toString(),
                    errorMessage.getErrorDescription());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
