package com.trips.busservice.service;

import com.trips.busservice.data.dto.BusDetailsDto;

import java.util.List;

public interface BusService {
    BusDetailsDto addBus(BusDetailsDto busDetailsDto);

    BusDetailsDto getBusById(String busId);

    BusDetailsDto updateSeats(String busId, int seats, String date);

    BusDetailsDto updateAvailability(String busId, boolean isAvailable, String date);

    BusDetailsDto updateBusDetails(String busId, BusDetailsDto busDetailsDto);

    List<BusDetailsDto> getAllBuses();

    List<BusDetailsDto> getBusByLocationAndDate(String from, String to, String dateString);
}

