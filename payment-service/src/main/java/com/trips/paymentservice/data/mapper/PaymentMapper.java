package com.trips.paymentservice.data.mapper;

import com.trips.paymentservice.data.dto.StripePaymentOrderRequestModel;
import com.trips.paymentservice.data.dto.StripePaymentOrderResponseModel;
import com.trips.paymentservice.data.dto.StripePaymentOrderSuccessRequest;
import com.trips.paymentservice.data.dto.StripePaymentOrderSuccessResponse;
import com.trips.paymentservice.data.entity.PaymentDetails;
import com.trips.paymentservice.data.model.*;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public StripePaymentOrderRequestModel toStripePaymentOrderRequestModel(PaymentOrderRequestModel paymentOrderRequestModel) {
        return StripePaymentOrderRequestModel.builder()
                .paymentMethodType(paymentOrderRequestModel.getPaymentMethodType())
                .bookingId(paymentOrderRequestModel.getBookingId())
                .roomId(paymentOrderRequestModel.getBusId())
                .roomTitle(paymentOrderRequestModel.getBusName())
                .uid(paymentOrderRequestModel.getUid())
                .amount(paymentOrderRequestModel.getAmount())
                .currency(paymentOrderRequestModel.getCurrency())
                .receipt(paymentOrderRequestModel.getReceipt())
                .build();
    }

    public PaymentOrderResponseModel toPaymentOrderResponseModel(StripePaymentOrderResponseModel stripePaymentOrderResponseModel) {
        return PaymentOrderResponseModel.builder()
                .id(stripePaymentOrderResponseModel.getId())
                .url(stripePaymentOrderResponseModel.getUrl())
                .status(stripePaymentOrderResponseModel.getStatus())
                .amount(stripePaymentOrderResponseModel.getAmount())
                .build();
    }

    public StripePaymentOrderSuccessRequest toRazorpayPaymentOrderSuccessRequest(PaymentOrderSuccessRequest paymentOrderSuccessRequest) {
        return StripePaymentOrderSuccessRequest.builder()
                .paymentMethodType(paymentOrderSuccessRequest.getPaymentMethodType())
                .orderCreationId(paymentOrderSuccessRequest.getOrderCreationId())
                .razorpayPaymentId(paymentOrderSuccessRequest.getPaymentServiceProviderPaymentId())
                .razorpayOrderId(paymentOrderSuccessRequest.getPaymentServiceProviderOrderId())
                .razorpaySignature(paymentOrderSuccessRequest.getPaymentServiceProviderSignature())
                .build();
    }

    public PaymentOrderSuccessResponse toPaymentOrderSuccessResponse(StripePaymentOrderSuccessResponse razorpayPaymentOrderSuccessResponse) {
        return PaymentOrderSuccessResponse.builder()
                .message(razorpayPaymentOrderSuccessResponse.getMessage())
                .build();
    }

    public PaymentDetailsResponseModel toPaymentDetailsResponseModel(PaymentDetails paymentDetails) {
        return PaymentDetailsResponseModel.builder()
                .paymentId(paymentDetails.getId())
                .type(paymentDetails.getType())
                .amount(paymentDetails.getAmount())
                .currency(paymentDetails.getCurrency())
                .bookingId(paymentDetails.getBookingId())
                .status(paymentDetails.getStatus())
                .createdAt(paymentDetails.getCreatedAt())
                .build();
    }
}
