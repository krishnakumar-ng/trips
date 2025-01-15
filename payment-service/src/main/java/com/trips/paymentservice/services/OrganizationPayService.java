package com.trips.paymentservice.services;


public interface OrganizationPayService {
    public boolean isOrganizationAccountActive(String organizationId);

    String getOrganizationStripeAccountId(String organizationId);
}
