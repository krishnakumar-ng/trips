package com.trips.paymentservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.paymentservice.constants.PaymentMethodType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDetailsResponseModel {
    @Schema(description = "payment ide", example = "cs_test_a1hzfOj0u9wL5JnnXYGBM8oWjNSKsTV5bdW66jlACOGh2ec4SpZurpiCBu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentId;

    @Schema(description = "payment type", example = "STRIPE", requiredMode = Schema.RequiredMode.REQUIRED)
    PaymentMethodType type;

    @Schema(description = "amount", example = "1200", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

    @Schema(description = "currency", example = "inr", requiredMode = Schema.RequiredMode.REQUIRED)
    String currency;

    @Schema(description = "bookingId", example = "bk_El440oZ8g9BNVIoGsO2t", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bookingId;

    @Schema(description = "status", example = "complete", requiredMode = Schema.RequiredMode.REQUIRED)
    String status;

    @Schema(description = "createdAt", example = "2024-02-23 09:14:50.567400", requiredMode = Schema.RequiredMode.REQUIRED)
    private ZonedDateTime createdAt;
}