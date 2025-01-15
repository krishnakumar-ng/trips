package com.trips.bookingservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.bookingservice.data.dto.PassengerDetailsDto;
import com.trips.bookingservice.enums.OnlinePaymentMethodType;
import com.trips.bookingservice.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBookingRequestModel {
    @Schema(description = "bus id", example = "room_kjansdf8u3wk976", requiredMode = Schema.RequiredMode.REQUIRED)
    private String busId;

    @Schema(description = "boarding-date", example = "2023-12-24T06:21:38.699Z", requiredMode = Schema.RequiredMode.REQUIRED)
    private String boardingDate;

    @Schema(description = "user id", example = "901237412412", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @Schema(description = "contact details")
    private ContactDetails contactDetails;

    @Schema(description = "passengers", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<PassengerDetailsDto> passengerDetailsList;

    @Schema(description = "payment type", example = "ONLINE", requiredMode = Schema.RequiredMode.REQUIRED)
    private PaymentType paymentType;

    @Schema(description = "online payment method type", example = "STRIPE", requiredMode = Schema.RequiredMode.REQUIRED)
    private OnlinePaymentMethodType onlinePaymentMethodType;
}