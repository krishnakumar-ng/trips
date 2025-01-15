package com.trips.busservice.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleDateDetailsDto {
    @JsonProperty("scheduleDateId")
    private Long scheduleDateId;

    @JsonProperty("availableSeats")
    private Integer availableSeats;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("isAvailable")
    private boolean isAvailable;
}
