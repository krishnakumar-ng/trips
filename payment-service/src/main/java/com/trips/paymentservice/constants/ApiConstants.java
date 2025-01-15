package com.trips.paymentservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstants {
    // API constants
    static final String SCHEME = "https";
    static final String HOSTNAME = "api.myroom.com";
    static final String VERSION = "v1";

    // API URI
    public static final String PAYMENT_SERVICE_API_V1 = "/api/v1/payment-service";
    public static final String PAYMENT_ORDER = "/orders";
    public static final String STRIPE_PAYMENT_EVENTS = "/stripe/events";

    public static final String PAYMENT_DASHBOARD = "/dashboard";
    public static final String PAYMENT_STATISTIC = "/statistics";
    public static final String PAYMENT_RECORDS = "/records";

    // API error code
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String ORGANIZATION_ACCOUNT_NOT_ACTIVE = "ORGANIZATION_ACCOUNT_NOT_ACTIVE";
    public static final String ORGANIZATION_ACCOUNT_NOT_FOUND = "ORGANIZATION_ACCOUNT_NOT_FOUND";
    public static final String INVALID_PAYMENT_METHOD_TYPE = "INVALID_PAYMENT_METHOD_TYPE";
    public static final String INVALID_PAYMENT = "INVALID_PAYMENT";
    public static final String INVALID_PRICE = "INVALID_PRICE";


    public static final String MESSAGE_SUCCESS = "Success";
    public static final String MESSAGE_BAD_REQUEST = "Bad Request";
    public static final String MESSAGE_UNAUTHORIZED = "Unauthorized";
    public static final String MESSAGE_NOT_FOUND = "Not Found";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String MESSAGE_SERVICE_UNAVAILABLE = "This service is currently unavailable";
    public static final String MESSAGE_INVALID_PRICE = "Invalid price";
}
