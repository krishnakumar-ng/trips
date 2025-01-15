package com.trips.paymentservice.services;

public interface StripePaymentValidationService {
    Boolean validateOrganizationAccountActive(String organizationId);
}
