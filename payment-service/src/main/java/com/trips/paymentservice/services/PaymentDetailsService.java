package com.trips.paymentservice.services;

import com.trips.paymentservice.data.dto.StripePaymentOrderRequestModel;
import com.trips.paymentservice.data.entity.PaymentDetails;
import com.stripe.model.checkout.Session;

public interface PaymentDetailsService {
    PaymentDetails savePayment(Session session, StripePaymentOrderRequestModel razorpayPaymentOrderRequestModel);
}
