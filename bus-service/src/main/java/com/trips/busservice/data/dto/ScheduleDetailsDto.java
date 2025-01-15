package com.trips.busservice.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trips.busservice.constants.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleDetailsDto {
    @JsonProperty("scheduleId")
    private Long scheduleId;

    @JsonProperty("departureTime")
    private LocalTime departureTime;

    @JsonProperty("arrivalTime")
    private LocalTime arrivalTime;

    @JsonProperty("frequency")
    private Frequency frequency;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("dates")
    private List<ScheduleDateDetailsDto> dates = new ArrayList<>();
}
