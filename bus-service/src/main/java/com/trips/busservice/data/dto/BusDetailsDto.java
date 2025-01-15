package com.trips.busservice.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trips.busservice.constants.BusType;
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
public class BusDetailsDto {

    @JsonProperty("busId")
    private String busId;

    @JsonProperty("busName")
    private String busName;

    @JsonProperty("busType")
    private BusType busType;

    @JsonProperty("operator")
    private OperatorDetailsDto operator;

    @JsonProperty("routes")
    private List<RouteDetailsDto> routes = new ArrayList<>();


    @JsonProperty("capacity")
    private Integer capacity;
}
