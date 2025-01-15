package com.trips.busservice.api.controller;

import com.trips.busservice.api.resource.BusResource;
import com.trips.busservice.data.dto.BusDetailsDto;
import com.trips.busservice.service.BusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BusController implements BusResource {
    private final BusService busService;

    @Override
    public ResponseEntity<BusDetailsDto> addBus(BusDetailsDto busDetailsDto) {
        return new ResponseEntity<>(busService.addBus(busDetailsDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BusDetailsDto> updateBusSeatsAvailability(String busId, boolean isAvailable, String date) {
        return new ResponseEntity<>(busService.updateAvailability(busId, isAvailable, date), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusDetailsDto> updateBusSeatsAvailability(String busId, int seats, String dateString) {
        return new ResponseEntity<>(busService.updateSeats(busId, seats, dateString), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusDetailsDto> updateBusDetails(String busId, @Valid BusDetailsDto busDetailsDto) {
        return new ResponseEntity<>(busService.updateBusDetails(busId, busDetailsDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BusDetailsDto>> getAllBuses() {
        return new ResponseEntity<>(busService.getAllBuses(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusDetailsDto> getBusById(String busId) {
        return new ResponseEntity<>(busService.getBusById(busId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BusDetailsDto>> getBusByLocationAndDate(String from, String to, String date) {
        return new ResponseEntity<>(busService.getBusByLocationAndDate(from, to, date), HttpStatus.OK);
    }
}
