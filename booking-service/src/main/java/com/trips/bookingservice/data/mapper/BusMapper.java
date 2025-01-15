package com.trips.bookingservice.data.mapper;

import com.trips.bookingservice.enums.OnlinePaymentMethodType;
import com.trips.bookingservice.data.dto.BusDetailsDto;
import com.trips.bookingservice.data.dto.BusPricesDto;
import com.trips.bookingservice.data.model.metadata.PaymentMetaDataModel;
import com.trips.bookingservice.data.model.metadata.BusMetaDataModel;
import com.trips.bookingservice.data.model.metadata.BusPriceMetaDataModel;
import com.trips.bookingservice.data.model.StripePaymentServiceProviderModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BusMapper {
    public BusMetaDataModel toBusMetaDataModel(BusDetailsDto busDetails) {
        BusMetaDataModel busMetaDataModel = new BusMetaDataModel();
        busMetaDataModel.setId(busDetails.getBusId());
        busMetaDataModel.setBusName(busDetails.getBusName());
        busMetaDataModel.setOperatorId(busDetails.getOperatorId());
        busMetaDataModel.setPrice(toBusPriceMetaDataModel(busDetails));
        return busMetaDataModel;
    }

    private BusPriceMetaDataModel toBusPriceMetaDataModel(BusDetailsDto busDetails) {
        BusPriceMetaDataModel busPriceMetaDataModel = new BusPriceMetaDataModel();
        busPriceMetaDataModel.setPrice(busDetails.getPrice().getPrice());
        busPriceMetaDataModel.setCurrency(busDetails.getPrice().getCurrency());
        return busPriceMetaDataModel;
    }

    public String toAmount(BusDetailsDto busDetails) {
        BigDecimal amount = new BigDecimal(0);
        BusPricesDto busPrices = busDetails.getPrice();
        amount = amount.add(busPrices.getPrice());
        return amount.toPlainString();
    }

    public PaymentMetaDataModel toBookingPaymentMetaDataModel(OnlinePaymentMethodType onlinePaymentMethodType, String amount, String currency, StripePaymentServiceProviderModel stripePaymentServiceProviderModel) {
        return PaymentMetaDataModel.builder()
                .onlinePaymentMethodType(onlinePaymentMethodType)
                .amount(amount)
                .currency(currency)
                .stripePaymentServiceProviderModel(stripePaymentServiceProviderModel)
                .build();
    }
}