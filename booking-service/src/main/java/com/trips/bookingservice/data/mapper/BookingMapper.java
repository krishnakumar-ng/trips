package com.trips.bookingservice.data.mapper;

import com.trips.bookingservice.data.model.BookingResponseModel;
import com.trips.bookingservice.enums.OnlinePaymentMethodType;
import com.trips.bookingservice.enums.PaymentType;
import com.trips.bookingservice.data.dto.PaymentRequestDto;
import com.trips.bookingservice.data.dto.PaymentResponseDto;
import com.trips.bookingservice.data.dto.StripePaymentOrderResponseDto;
import com.trips.bookingservice.data.entity.BookingEntity;
import com.trips.bookingservice.data.model.StripePaymentServiceProviderModel;
import com.trips.bookingservice.enums.StripePaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class BookingMapper {
    public BookingResponseModel toBookingResponseModel(PaymentResponseDto paymentResponse, BookingEntity bookingEntity) {
        BookingResponseModel bookingResponseModel = new BookingResponseModel();
        bookingResponseModel.setPaymentType(PaymentType.ONLINE_PAYMENT);
        bookingResponseModel.setId(bookingEntity.getId());
        bookingResponseModel.setPaymentOrderId(paymentResponse.getId());
        bookingResponseModel.setStatus(bookingEntity.getStatus());
        bookingResponseModel.setAmount(new BigDecimal(paymentResponse.getAmount()));
        bookingResponseModel.setPaymentUrl(paymentResponse.getUrl());
        return bookingResponseModel;
    }

    public BookingResponseModel toBookingOrderResponseModel(BookingEntity bookingEntity) {
        BookingResponseModel bookingResponseModel = new BookingResponseModel();
        bookingResponseModel.setPaymentType(bookingEntity.getPaymentType());
        bookingResponseModel.setStatus(bookingEntity.getStatus());
        bookingResponseModel.setId(bookingResponseModel.getId());
        if (bookingEntity.getPaymentType().equals(PaymentType.ONLINE_PAYMENT)) {
            if (bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType().equals(OnlinePaymentMethodType.STRIPE)) {
                StripePaymentServiceProviderModel stripePaymentServiceProviderModel = bookingEntity.getPaymentMetaDataModel().getStripePaymentServiceProviderModel();
                bookingResponseModel.setPaymentOrderId(stripePaymentServiceProviderModel.getId());
                bookingResponseModel.setAmount(new BigDecimal(stripePaymentServiceProviderModel.getAmount()));
                bookingResponseModel.setPaymentUrl(stripePaymentServiceProviderModel.getUrl());
            } else {
                log.info("Invalid Payment Type.");
            }
        }
        return bookingResponseModel;
    }

    public PaymentResponseDto toOnlinePaymentOrderResponseDto(StripePaymentOrderResponseDto stripePaymentOrderResponseDto) {
        return PaymentResponseDto.builder()
                .id(stripePaymentOrderResponseDto.getId())
                .amount(stripePaymentOrderResponseDto.getAmount())
                .status(stripePaymentOrderResponseDto.getStatus())
                .url(stripePaymentOrderResponseDto.getUrl())
                .build();
    }

    public PaymentRequestDto toPaymentOrderRequestDto(BookingEntity bookingEntity) {
        return PaymentRequestDto.builder()
                .onlinePaymentMethodType(bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType())
                .amount(bookingEntity.getAmount().toString())
                .currency(bookingEntity.getPaymentMetaDataModel().getCurrency())
                .receipt("#bookingId: " + bookingEntity.getId())
                .busId(bookingEntity.getBusDetails().getId())
                .operatorId(bookingEntity.getBusDetails().getOperatorId())
                .bookingId(bookingEntity.getId())
                .busName(bookingEntity.getBusDetails().getBusName())
                .userId(bookingEntity.getUserId())
                .build();
    }

    public StripePaymentServiceProviderModel toStripePaymentServiceProvider(PaymentResponseDto paymentResponseDto) {
        return StripePaymentServiceProviderModel.builder()
                .id(paymentResponseDto.getId())
                .amount(paymentResponseDto.getAmount())
                .status(StripePaymentStatus.valueOf(paymentResponseDto.getStatus()))
                .url(paymentResponseDto.getUrl())
                .build();
    }

    public StripePaymentOrderResponseDto toStripePaymentOrderResponseDto(PaymentResponseDto paymentResponseDto) {
        return StripePaymentOrderResponseDto.builder()
                .id(paymentResponseDto.getId())
                .amount(paymentResponseDto.getAmount())
                .status(paymentResponseDto.getStatus())
                .url(paymentResponseDto.getUrl())
                .build();
    }

    public BookingResponseModel toBookingResponseModel(BookingEntity bookingEntity) {
        return BookingResponseModel.builder()
                .id(bookingEntity.getId())
                .amount(bookingEntity.getAmount())
                .boardingDate(bookingEntity.getBoardingDate().toString())
                .busDetails(bookingEntity.getBusDetails())
                .status(bookingEntity.getStatus())
                .passengerDetailsList(bookingEntity.getPassengerDetailsList())
                .contactDetails(bookingEntity.getContactDetails())
                .paymentType(bookingEntity.getPaymentType())
                .bookingDate(bookingEntity.getBookingDate())
                .userId(bookingEntity.getUserId())
                .createdDate(bookingEntity.getCreatedDate())
                .lastUpdateDate(bookingEntity.getLastUpdateDate())
                .build();
    }
}