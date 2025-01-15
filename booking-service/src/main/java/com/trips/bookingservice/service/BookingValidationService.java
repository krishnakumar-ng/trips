package com.trips.bookingservice.service;


import com.trips.bookingservice.data.model.CreateBookingRequestModel;
import com.trips.bookingservice.enums.GenericValidator;

public interface BookingValidationService {
    GenericValidator validateBookingRequestData(CreateBookingRequestModel bookingRequestModel);
    void validateCurrentUser(String currentUserUid, String prevUserUid);
}
