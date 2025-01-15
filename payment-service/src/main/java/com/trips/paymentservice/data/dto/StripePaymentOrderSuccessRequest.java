package com.trips.paymentservice.data.dto;

import com.trips.paymentservice.constants.PaymentMethodType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StripePaymentOrderSuccessRequest {
    private PaymentMethodType paymentMethodType;

    private String orderCreationId;

    private String razorpayPaymentId;

    private String razorpayOrderId;

    private String razorpaySignature;
}
