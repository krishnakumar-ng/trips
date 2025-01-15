package com.trips.paymentservice.services;

import com.trips.paymentservice.data.dto.BookingDataResponseDto;

public interface BookingService {
    BookingDataResponseDto getBookingDetails(String bookingId);
}
