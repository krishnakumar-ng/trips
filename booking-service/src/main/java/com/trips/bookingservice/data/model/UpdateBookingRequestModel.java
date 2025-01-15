package com.trips.bookingservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.bookingservice.data.dto.PassengerDetailsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBookingRequestModel {
    @Schema(description = "booking request id",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String bookingRequestId;

    @Schema(description = "boarding-date", example = "2023-12-24T06:21:38.699Z")
    private String boardingDate;

    @Schema(description = "passengers")
    private List<PassengerDetailsDto> passengerDetailsList;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String busId;

    @Schema(description = "user id", example = "901237412412", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @Schema(description = "status", example = "901237412412", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean status;
}