package com.trips.bookingservice.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import static com.trips.bookingservice.constants.ApiConstants.*;


@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8083")
public interface PaymentServiceClient {
    @PostMapping("/" + API + "/" + VERSION1 + PAYMENT_SERVICE_ENDPOINT)
    ResponseEntity<String> makePayment();
}
