package com.trips.api.gateway.feign.client;

import com.trips.api.gateway.data.dto.VerifyTokenResponseDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(name = "AUTH-SERVER", url = "http://localhost:9000")
public interface AuthClient {
    @GetMapping("/verify-token")
    @Headers({AUTHORIZATION,"{token}"})
    ResponseEntity<VerifyTokenResponseDto> verifyToken(@RequestParam("token") String token);
}
