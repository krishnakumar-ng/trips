package com.trips.bookingservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstants {
    // API constants
    public static final String SCHEME = "https";
    public static final String HOSTNAME = "trips.com";
    public static final String VERSION1 = "v1";
    public static final String VERSION2 = "v2";
    public static final String API = "api";

    // API URI
    public static final String BOOKING_SERVICE_ENDPOINT =  "/booking-service";
    public static final String BUS_SERVICE_ENDPOINT = "/bus-service";
    public static final String OPERATOR_SERVICE_ENDPOINT =  "/operator-service";
    public static final String PAYMENT_SERVICE_ENDPOINT = "/payment-service";
    public static final String BOOKING_REQUEST = "/request";
    public static final String BOOKING_DASHBOARD = "/dashboard";
    public static final String BOOKING_STATISTIC = "/statistics";
    public static final String BOOKING_RECORDS = "/records";


    // API error code
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String INVALID_BOOKING_REQUEST_DATA = "MESSAGE_INVALID_BOOKING_REQUEST_DATA";
    public static final String INVALID_PAYMENT_TYPE = "INVALID_PAYMENT_METHOD_TYPE";
    public static final String INVALID_PAYMENT_METHOD_TYPE = "INVALID_PAYMENT_METHOD_TYPE";
    public static final String INVALID_BOOKING_REQUEST_ID = "INVALID_BOOKING_REQUEST_ID";
    public static final String ROOM_UNAVAILABLE = "ROOM_UNAVAILABLE";


    public static final String MESSAGE_SUCCESS = "Success";
    public static final String MESSAGE_BAD_REQUEST = "Bad Request";
    public static final String MESSAGE_UNAUTHORIZED = "Unauthorized";
    public static final String MESSAGE_INVALID_BOOKING_REQUEST_DATA = "Invalid booking request data";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String MESSAGE_NOT_FOUND = "Not found";
    public static final String MESSAGE_SERVICE_UNAVAILABLE = "This service is currently unavailable";
}