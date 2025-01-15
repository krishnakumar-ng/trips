package com.trips.bookingservice.service.impl;

import com.trips.bookingservice.constants.ApiConstants;
import com.trips.bookingservice.data.dto.BusSeatAvailabilityRequestDto;
import com.trips.bookingservice.data.dto.BusAvailabilityResponseDto;
import com.trips.bookingservice.data.dto.BusDetailsDto;
import com.trips.bookingservice.feign.clients.BusServiceClient;
import com.trips.bookingservice.service.BusService;
import com.trips.bookingservice.utils.EurekaClientUtil;
import com.trips.bookingservice.exception.BookingServiceRuntimeException;
import com.trips.bookingservice.exception.BusUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final EurekaClientUtil eurekaClientUtil;
    private final BusServiceClient busServiceClient;

    @Value("${services.bus-service.id}")
    String busServiceId;

    @Value("${services.bus-service.v1.api}")
    String busServiceV1;

    @Value("${services.bus-service.v1.name}")
    String busServiceV1Name;


    @Override
    public boolean checkAvailability(BusSeatAvailabilityRequestDto busSeatAvailabilityRequestDto) {
        log.info("calling {} Service for checking room availability for: {}", busServiceV1Name, busSeatAvailabilityRequestDto);

        boolean busAvailability;

        // Construct URL
        String roomServiceUri = eurekaClientUtil.getServiceUri(busServiceId);
        String url = roomServiceUri + busServiceV1 + "/" + busSeatAvailabilityRequestDto.getBusId() + "/availability";

        try {
            BusAvailabilityResponseDto response = busServiceClient.checkAvailability(busSeatAvailabilityRequestDto).getBody();

            log.info("Room service response: {}", response);

            if (!response.isAvailable()) {
                log.info("Bus is unavailable");
                throw new BusUnavailableException(ApiConstants.ROOM_UNAVAILABLE, response.getMessage(), "");
            }

            busAvailability = true;
        } catch (Exception ex) {
            log.error("Some error occurred: {}", ex.getMessage());
            throw new BookingServiceRuntimeException(ex.getMessage());
        }

        return busAvailability;
    }

    @Override
    public BusDetailsDto getBus(String busId) {
        log.info("calling Room Service for getting room details");

        BusDetailsDto busDetails;

        // Construct URL
        String roomServiceUri = eurekaClientUtil.getServiceUri(busServiceId);
        String url = roomServiceUri + busServiceV1 + "/" + busId;

        try {
            BusDetailsDto response = busServiceClient.getBusById(busId).getBody();

            log.info("{} response: {}", busServiceV1Name, response);

            busDetails = response;
        } catch (Exception ex) {
            log.error("Some error occurred: {}", ex.getMessage());
            throw new BookingServiceRuntimeException(ex.getMessage());
        }

        return busDetails;
    }
}