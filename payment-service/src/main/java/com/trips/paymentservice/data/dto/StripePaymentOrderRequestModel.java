package com.trips.paymentservice.data.dto;

import com.trips.paymentservice.constants.PaymentMethodType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StripePaymentOrderRequestModel {
    private String bookingId;

    private String roomId;

    private String roomTitle;

    private String uid;

    private BigDecimal amount;

    private String currency;

    private String receipt;

    private String organizationId;

    private PaymentMethodType paymentMethodType;
}
