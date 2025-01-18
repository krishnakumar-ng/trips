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
        log.info("Adding bus : {}", busDetailsDto);
        BusDetailsDto savedBus = busService.addBus(busDetailsDto);
        log.info("Added bus with id: {}", savedBus.getBusId());
        return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BusDetailsDto> updateBusAvailability(String busId, boolean isAvailable, String date) {
        log.info("Updating bus seats availability for busId : {}", busId);
        BusDetailsDto savedBus = busService.updateAvailability(busId, isAvailable, date);
        log.info("Updated bus seats availability to {} for busId: {}", isAvailable, savedBus.getBusId());
        return new ResponseEntity<>(savedBus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusDetailsDto> updateBusSeatsAvailability(String busId, int seats, String dateString) {
        log.info("Updating bus seats for busId : {}", busId);
        BusDetailsDto savedBus = busService.updateSeats(busId, seats, dateString);
        log.info("Updated bus seats for busId: {}", savedBus.getBusId());
        return new ResponseEntity<>(savedBus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusDetailsDto> updateBusDetails(String busId, @Valid BusDetailsDto busDetailsDto) {
        log.info("Updating bus details for busId : {}", busId);
        BusDetailsDto savedBus = busService.updateBusDetails(busId, busDetailsDto);
        log.info("Updated bus details : {} for busId: {}", savedBus, savedBus.getBusId());
        return new ResponseEntity<>(savedBus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BusDetailsDto>> getAllBuses() {
        log.info("Getting all the buses from the table.");
        List<BusDetailsDto> savedBusList = busService.getAllBuses();
        return new ResponseEntity<>(savedBusList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BusDetailsDto> getBusById(String busId) {
        log.info("Getting buses details for busId : {}",busId);
        BusDetailsDto savedBus = busService.getBusById(busId);
        return new ResponseEntity<>(savedBus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BusDetailsDto>> getBusByLocationAndDate(String from, String to, String date) {
        log.info("Getting all the buses for start location: {} and destinationLocation : {} with date: {}",from,to,date);
        List<BusDetailsDto> savedBusList = busService.getBusByLocationAndDate(from, to, date);
        return new ResponseEntity<>(savedBusList, HttpStatus.OK);
    }
}
