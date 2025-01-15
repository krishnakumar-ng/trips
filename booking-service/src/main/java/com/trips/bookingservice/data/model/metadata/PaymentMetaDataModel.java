package com.trips.bookingservice.data.model.metadata;

import com.trips.bookingservice.data.model.StripePaymentServiceProviderModel;
import com.trips.bookingservice.enums.OnlinePaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMetaDataModel {
    private OnlinePaymentMethodType onlinePaymentMethodType;
    private String amount;
    private String currency;
    private StripePaymentServiceProviderModel stripePaymentServiceProviderModel;
}
