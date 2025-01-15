package com.trips.paymentservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.paymentservice.constants.PaymentMethodType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOrderRequestModel {
    @Schema(description = "payment method type", example = "STRIPE", requiredMode = Schema.RequiredMode.REQUIRED)
    private PaymentMethodType paymentMethodType;

    @Schema(description = "bookingId", example = "13124", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bookingId;

    @Schema(description = "busId", example = "87134", requiredMode = Schema.RequiredMode.REQUIRED)
    private String busId;

    @Schema(description = "busName", example = "87134", requiredMode = Schema.RequiredMode.REQUIRED)
    private String busName;

    @Schema(description = "user id", example = "901237412412", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;

    @Schema(description = "amount", example = "299", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

    @Schema(description = "currency", example = "INR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String currency;

    @Schema(description = "receipt", example = "receipt#1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String receipt;

}