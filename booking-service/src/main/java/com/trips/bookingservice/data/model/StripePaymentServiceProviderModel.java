package com.trips.bookingservice.data.model;

import com.trips.bookingservice.enums.StripePaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StripePaymentServiceProviderModel {
    private String id;
    private String amount;
    private StripePaymentStatus status;
    private String url;
}