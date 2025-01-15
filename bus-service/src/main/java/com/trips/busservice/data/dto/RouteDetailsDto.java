package com.trips.busservice.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteDetailsDto {
    @JsonProperty("routeId")
    private String routeId;

    @JsonProperty("sourceCity")
    private String sourceCity;

    @JsonProperty("destinationCity")
    private String destinationCity;

    @JsonProperty("stops")
    private List<StopDetailsDto> stops = new ArrayList<>();

    @JsonProperty("schedules")
    private List<ScheduleDetailsDto> schedules = new ArrayList<>();

}
