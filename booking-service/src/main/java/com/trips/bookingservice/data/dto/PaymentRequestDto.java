package com.trips.bookingservice.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.bookingservice.enums.OnlinePaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequestDto {
    private OnlinePaymentMethodType onlinePaymentMethodType;
    private String amount;
    private String currency;
    private String bookingId;
    private String receipt;
    private String busId;
    private String busName;
    private String userId;
    private String operatorId;
}