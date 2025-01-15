package com.trips.busservice.service;

import com.trips.busservice.data.entity.*;
import com.trips.busservice.data.mapper.BusMapper;
import com.trips.busservice.data.dto.BusDetailsDto;
import com.trips.busservice.exceptions.BusNotAvailableException;
import com.trips.busservice.exceptions.BusNotFoundException;
import com.trips.busservice.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;
    private final OperatorRepository operatorRepository;
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleDateRepository scheduleDateRepository;
    private final BusMapper busMapper;

    @Override
    public BusDetailsDto addBus(BusDetailsDto busDetailsDto) {
        BusEntity busEntity = busMapper.toBusEntity(busDetailsDto);
        log.info("Bus Model: {}", busDetailsDto);
        log.info("Bus Entity: {}", busEntity);

        // Save the Operator entity
        OperatorEntity operator = busEntity.getOperator();
        operator = operatorRepository.save(operator);
        busEntity.setOperator(operator);

        // Save the Bus entity
        BusEntity savedBusEntity = busRepository.save(busEntity);

        // Save Routes, Stops, Schedules, and ScheduleDates
        for (RouteEntity routeEntity : busEntity.getRoutes()) {
            routeEntity.setBus(savedBusEntity);
            RouteEntity savedRoute = routeRepository.save(routeEntity);

            for (StopEntity stop : routeEntity.getStops()) {
                stop.setRoute(savedRoute);
                stopRepository.save(stop);
            }
            for (ScheduleEntity schedule : routeEntity.getSchedules()) {
                schedule.setBus(savedBusEntity);
                schedule.setRoute(savedRoute);
                ScheduleEntity savedSchedule = scheduleRepository.save(schedule);

                for (ScheduleDateEntity scheduleDate : schedule.getDates()) {
                    scheduleDate.setSchedule(savedSchedule);
                    scheduleDateRepository.save(scheduleDate);
                }
            }
        }
        return busMapper.toBusDetailsDto(savedBusEntity);
    }

    @Override
    public BusDetailsDto getBusById(String busId) {
        Optional<BusEntity> busOptional = busRepository.findById(busId);
        return busOptional.map(busMapper::toBusDetailsDto)
                .orElseThrow(() -> new BusNotFoundException("No Bus exists with id - " + busId));

    }

    @Override
    public BusDetailsDto updateSeats(String busId, int seats, String date) {
        LocalDate localDate = LocalDate.parse(date);
        BusEntity bus = busRepository.findById(busId)
                .orElseThrow(() -> new BusNotFoundException("No Bus exists with id - " + busId));

        // Retrieve all schedules associated with the bus
        List<ScheduleEntity> schedules = scheduleRepository.findByBus(bus);

        for (ScheduleEntity schedule : schedules) {
            List<ScheduleDateEntity> scheduleDates = schedule.getDates();
            for (ScheduleDateEntity scheduleDate : scheduleDates) {
                if (scheduleDate.getDate().isEqual(localDate)) {
                    if (scheduleDate.isAvailable())
                        scheduleDate.setAvailableSeats(scheduleDate.getAvailableSeats() - seats);
                    else
                        throw new BusNotAvailableException("Bus is not available in the given date - " + date);
                }
            }
            scheduleRepository.save(schedule); // Save the updated schedule
        }

        return busMapper.toBusDetailsDto(bus);
    }

    @Override
    public BusDetailsDto updateAvailability(String busId, boolean isAvailable, String date) {
        LocalDate localDate = LocalDate.parse(date);
        BusEntity bus = busRepository.findById(busId)
                .orElseThrow(() -> new BusNotFoundException("No Bus exists with id - " + busId));

        // Retrieve all schedules associated with the bus
        List<ScheduleEntity> schedules = scheduleRepository.findByBus(bus);

        for (ScheduleEntity schedule : schedules) {
            List<ScheduleDateEntity> scheduleDates = schedule.getDates();
            for (ScheduleDateEntity scheduleDate : scheduleDates) {
                if (scheduleDate.getDate().isEqual(localDate))
                    scheduleDate.setAvailable(isAvailable);
            }
            scheduleRepository.save(schedule); // Save the updated schedule
        }

        return busMapper.toBusDetailsDto(bus);
    }

    @Override
    public BusDetailsDto updateBusDetails(String busId, BusDetailsDto busDetailsDto) {
        Optional<BusEntity> busOptional = busRepository.findById(busId);
        if (busOptional.isPresent()) {
            BusEntity bus = busOptional.get();
            // Update bus details with values from busModel
            bus.setBusName(busDetailsDto.getBusName());
            bus.setBusType(busDetailsDto.getBusType());
            // Update operator, routes, etc. as needed
            bus = busRepository.save(bus);
            return busMapper.toBusDetailsDto(bus);
        }
        return null;
    }

    @Override
    public List<BusDetailsDto> getAllBuses() {
        List<BusEntity> allBuses = busRepository.findAll();
        return allBuses.stream()
                .map(busMapper::toBusDetailsDto)
                .toList();
    }

    @Override
    public List<BusDetailsDto> getBusByLocationAndDate(String from, String to, String dateString) {
        LocalDate travelDate = LocalDate.parse(dateString);
        List<RouteEntity> routes = routeRepository.findBySourceCityAndDestinationCity(from, to);
        List<BusEntity> availableBuses = new ArrayList<>();

        for (RouteEntity route : routes) {
            List<ScheduleEntity> schedules = scheduleRepository.findByRouteAndDate(route.getRouteId(), travelDate, true);
            if (!schedules.isEmpty()) {
                for (ScheduleEntity schedule : schedules) {
                    if (!availableBuses.contains(schedule.getBus())) {
                        availableBuses.add(schedule.getBus());
                    }
                }
            }
        }

        return availableBuses.stream()
                .map(busMapper::toBusDetailsDto)
                .toList();
    }
}
