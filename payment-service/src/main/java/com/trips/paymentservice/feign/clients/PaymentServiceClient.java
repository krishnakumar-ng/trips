package com.trips.paymentservice.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;



@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8085")
public interface PaymentServiceClient {
//    @PostMapping(VERSION1 + PAYMENT_SERVICE_ENDPOINT)
//    ResponseEntity<PaymentModel> makePayment(PaymentModel paymentModel);
}
