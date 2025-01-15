package com.trips.paymentservice.feign.clients;

import com.trips.paymentservice.data.dto.UpdateBusStatusPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "BUS-SERVICE", url = "http://localhost:8082")
public interface BusServiceClient {
    //    @GetMapping(VERSION1 + BUS_SERVICE_ENDPOINT)
//    ResponseEntity<BusDetailsDto> getBusById(String busId);
//    @GetMapping(VERSION1 + BUS_SERVICE_ENDPOINT)
//    ResponseEntity<BusAvailabilityResponse> checkAvailability(BusAvailabilityRequest busAvailabilityRequest);
    String updateBusStatus(UpdateBusStatusPayload updateBusStatusPayload);
}
