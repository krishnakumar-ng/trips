package com.trips.paymentservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOrderSuccessResponse {
    @Schema(description = "message", example = "Payment successful!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;
}
