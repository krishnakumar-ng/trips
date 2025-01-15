package com.trips.bookingservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.bookingservice.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePaymentTypeRequestModel {
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    String bookingRequestId;

    @Schema(description = "user id", example = "901237412412", requiredMode = Schema.RequiredMode.REQUIRED)
    String userId;

    @Schema(description = "payment type", example = "ONLINE_PAYMENT", requiredMode = Schema.RequiredMode.REQUIRED)
    PaymentType paymentType;
}
