package com.trips.bookingservice.service;

import com.trips.bookingservice.data.dto.BusSeatAvailabilityRequestDto;
import com.trips.bookingservice.data.dto.BusDetailsDto;

public interface BusService {
    boolean checkAvailability(BusSeatAvailabilityRequestDto busSeatAvailabilityRequestDto);

    BusDetailsDto getBus(String busId);
}