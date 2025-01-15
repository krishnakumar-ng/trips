package com.trips.paymentservice.services;

import com.trips.paymentservice.data.dto.StripePaymentOrderRequestModel;
import com.trips.paymentservice.data.dto.StripePaymentOrderResponseModel;
import com.trips.paymentservice.data.dto.StripePaymentOrderSuccessRequest;
import com.trips.paymentservice.data.dto.StripePaymentOrderSuccessResponse;

public interface StripePaymentService {
    StripePaymentOrderResponseModel createOrder(StripePaymentOrderRequestModel razorpayPaymentOrderRequestModel);

    StripePaymentOrderSuccessResponse handlePaymentOrderSuccess(StripePaymentOrderSuccessRequest razorpayPaymentOrderSuccessRequest);
}
