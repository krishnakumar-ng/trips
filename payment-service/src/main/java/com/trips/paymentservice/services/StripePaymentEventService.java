package com.trips.paymentservice.services;

import com.stripe.model.StripeObject;

public interface StripePaymentEventService {
    void handlePaymentCompletedUpdatedEvent(StripeObject stripeObject);
}