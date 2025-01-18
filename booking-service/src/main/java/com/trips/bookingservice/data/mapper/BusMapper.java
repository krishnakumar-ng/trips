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
        busMetaDataModel.setOperatorId(busDetails.getOperator().getOperatorName());
        busMetaDataModel.setPrice(toBusPriceMetaDataModel(busDetails));
        return busMetaDataModel;
    }

    private BusPriceMetaDataModel toBusPriceMetaDataModel(BusDetailsDto busDetails) {
        BusPriceMetaDataModel busPriceMetaDataModel = new BusPriceMetaDataModel();
        busPriceMetaDataModel.setPrice(getBusPrice(busDetails));
        busPriceMetaDataModel.setCurrency("INR");
        return busPriceMetaDataModel;
    }

    public String toAmount(BusDetailsDto busDetails) {
        BigDecimal amount = new BigDecimal(0);
        BigDecimal busPrice = getBusPrice(busDetails);
        amount = amount.add(busPrice);
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

    public BigDecimal getBusPrice(BusDetailsDto busDetails) {

        // Currently price of the first schedule is picked
        // (need to adjust this based on your logic)
        if (busDetails.getRoutes() != null && !busDetails.getRoutes().isEmpty()
                && busDetails.getRoutes().getFirst().getSchedules() != null
                && !busDetails.getRoutes().getFirst().getSchedules().isEmpty()) {
            return busDetails.getRoutes().getFirst().getSchedules().getFirst().getPrice();
        }

        return null; // Or throw an exception if price is not available
    }
}