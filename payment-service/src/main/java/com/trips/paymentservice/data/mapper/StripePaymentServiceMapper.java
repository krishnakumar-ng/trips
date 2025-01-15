package com.trips.paymentservice.data.mapper;

import com.trips.paymentservice.data.dto.StripeCreateSessionObjectDto;
import com.trips.paymentservice.data.dto.StripePaymentOrder;
import com.trips.paymentservice.data.dto.StripePaymentOrderRequestModel;
import com.trips.paymentservice.data.dto.StripePaymentOrderResponseModel;
import com.trips.paymentservice.data.entity.PaymentDetails;
import com.stripe.model.checkout.Session;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentServiceMapper {
    public StripeCreateSessionObjectDto toStripeCreateSessionObjectDto(StripePaymentOrderRequestModel stripePaymentOrderRequestModel) {
        return StripeCreateSessionObjectDto.builder()
                .bookingId(stripePaymentOrderRequestModel.getBookingId())
                .roomId(stripePaymentOrderRequestModel.getRoomId())
                .title(stripePaymentOrderRequestModel.getRoomTitle())
                .uid(stripePaymentOrderRequestModel.getUid())
                .amount(stripePaymentOrderRequestModel.getAmount())
                .currency(stripePaymentOrderRequestModel.getCurrency())
                .receipt(stripePaymentOrderRequestModel.getReceipt())
                .organizationId(stripePaymentOrderRequestModel.getOrganizationId())
                .build();
    }

    public StripePaymentOrderResponseModel toStripePaymentOrderResponseModel(StripePaymentOrder stripePaymentOrder) {
        return StripePaymentOrderResponseModel.builder()
                .id(stripePaymentOrder.getId())
                .url(stripePaymentOrder.getUrl())
                .status(stripePaymentOrder.getStatus())
                .amount(stripePaymentOrder.getAmount())
                .build();
    }

    public PaymentDetails toPaymentDetails(Session session, StripePaymentOrderRequestModel stripePaymentOrderRequestModel) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentId(session.getId());
        paymentDetails.setType(stripePaymentOrderRequestModel.getPaymentMethodType());
        paymentDetails.setAmount(stripePaymentOrderRequestModel.getAmount());
        paymentDetails.setCurrency(stripePaymentOrderRequestModel.getCurrency());
        paymentDetails.setBookingId(stripePaymentOrderRequestModel.getBookingId());
        paymentDetails.setUid(stripePaymentOrderRequestModel.getUid());
        paymentDetails.setStatus(session.getStatus());
        return paymentDetails;
    }
}
