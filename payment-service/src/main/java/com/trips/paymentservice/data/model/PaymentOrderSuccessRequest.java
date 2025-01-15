package com.trips.paymentservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.paymentservice.constants.PaymentMethodType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOrderSuccessRequest {
    @Schema(description = "payment method type", example = "RAZORPAY", requiredMode = Schema.RequiredMode.REQUIRED)
    private PaymentMethodType paymentMethodType;

    @Schema(description = "order creationId", example = "order_NEhL3xDPU42mxj", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderCreationId;

    @Schema(description = "razorpay PaymentId", example = "pay_29QQoUBi66xm2f", requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentServiceProviderPaymentId;

    @Schema(description = "razorpay orderId", example = "order_9A33XWu170gUtm", requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentServiceProviderOrderId;

    @Schema(description = "razorpay signature", example = "9ef4dffbfd84f1318f6739a3ce19f9d85851857ae648f114332d8401e0949a3d", requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentServiceProviderSignature;
}
