package com.trips.paymentservice.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "OPERATOR-SERVICE", url = "http://localhost:8083")
public interface OperatorServiceClient {
//    @GetMapping(VERSION1 + OPERATOR_SERVICE_ENDPOINT)
//    ResponseEntity<OperatorData> getOperatorById(String busId);
}
