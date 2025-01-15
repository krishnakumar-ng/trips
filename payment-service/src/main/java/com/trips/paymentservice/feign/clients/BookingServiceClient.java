package com.trips.paymentservice.feign.clients;

import com.trips.paymentservice.data.dto.BookingDataResponseDto;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "BOOKING-SERVICE", url = "http://localhost:8081")
public interface BookingServiceClient {
    BookingDataResponseDto getBookingDetails(String bookingId);
}
