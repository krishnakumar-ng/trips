package com.trips.bookingservice.service;

import com.trips.bookingservice.data.dto.PaymentRequestDto;
import com.trips.bookingservice.data.dto.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto createPaymentOrder(PaymentRequestDto paymentRequestDto);
}