package com.trips.paymentservice.constants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaymentNote {
    @Schema(description = "note key", example = "uid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;

    @Schema(description = "note value", example = "8723tr875baw673", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;
}
