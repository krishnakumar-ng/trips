package com.trips.bookingservice.feign.clients;

import com.trips.bookingservice.data.dto.BusDetailsDto;
import com.trips.bookingservice.data.dto.BusSeatAvailabilityRequestDto;
import com.trips.bookingservice.data.dto.BusAvailabilityResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.trips.bookingservice.constants.ApiConstants.*;

@FeignClient(name = "BUS-SERVICE", url = "http://localhost:8082")
public interface BusServiceClient {
    @GetMapping("/" + API + "/" + VERSION1 + BUS_SERVICE_ENDPOINT + "/{busId}")
    ResponseEntity<BusDetailsDto> getBusById(@PathVariable("busId") String busId);

    @GetMapping("/" + API + "/" + VERSION1 + BUS_SERVICE_ENDPOINT)
    ResponseEntity<BusAvailabilityResponseDto> checkAvailability(BusSeatAvailabilityRequestDto busSeatAvailabilityRequestDto);
}
