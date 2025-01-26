package com.trips.api.gateway.validator;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/auth/api/v1/verify-token",
            "/auth/api/v1/users"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> {
        String path = serverHttpRequest.getURI().getPath();

        if (path.startsWith("/api/v1/bus-service") || path.startsWith("/api/v1/booking-service")
                && serverHttpRequest.getMethod() == HttpMethod.GET) {
            return false;
        }

        return openApiEndpoints.stream()
                .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
    };
}
