package com.trips.bookingservice.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {
   private Integer errorCode;
   private HttpStatus errorStatus;
   private String errorDescription;
}
