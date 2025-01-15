package com.trips.bookingservice.service.impl;

import com.trips.bookingservice.constants.ApiConstants;
import com.trips.bookingservice.data.dto.BusSeatAvailabilityRequestDto;
import com.trips.bookingservice.data.model.CreateBookingRequestModel;
import com.trips.bookingservice.enums.GenericValidator;
import com.trips.bookingservice.exception.BookingRequestUnauthorizedAccessException;
import com.trips.bookingservice.service.BookingValidationService;
import com.trips.bookingservice.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingValidationServiceImpl implements BookingValidationService {

    @Override
    public GenericValidator validateBookingRequestData(CreateBookingRequestModel bookingRequestModel) {
        return GenericValidator.VALID;
    }

    @Override
    public void validateCurrentUser(String currentUserUid, String prevUserUid) {
        log.info("Validating current user is the one who created the booking request");
        if (!currentUserUid.equals(prevUserUid)) {
            log.error("Unauthorized access: Current user '{}' is not the creator of the booking request", currentUserUid);
            throw new BookingRequestUnauthorizedAccessException(ApiConstants.UNAUTHORIZED, "You do not have permission to modify this booking request.", "");
        }
        log.info("Validated current user");
    }

}
