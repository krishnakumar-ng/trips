package com.trips.paymentservice.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StripePaymentOrderSuccessResponse {
    private String message;
}
