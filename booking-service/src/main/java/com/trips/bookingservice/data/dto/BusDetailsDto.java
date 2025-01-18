package com.trips.bookingservice.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusDetailsDto {
    private String busId;
    private String busName;
    private String busType;
    private Operator operator;
    private List<Route> routes;
    private int capacity;

    @Data
    public static class Operator {
        private String operatorName;
        private String contactInfo;
    }

    @Data
    public static class Route {
        private String routeId;
        private String sourceCity;
        private String destinationCity;
        private List<Stop> stops;
        private List<Schedule> schedules;
    }

    @Data
    public static class Stop {
        private String stopId;
        private String stopName;
        private int stopOrder;
    }

    @Data
    public static class Schedule {
        private int scheduleId;
        private String departureTime;
        private String arrivalTime;
        private String frequency;
        private BigDecimal price;
        private List<ScheduleDate> dates;
    }

    @Data
    public static class ScheduleDate {
        private int scheduleDateId;
        private int availableSeats;
        private String date;
        private boolean isAvailable;
    }
}
