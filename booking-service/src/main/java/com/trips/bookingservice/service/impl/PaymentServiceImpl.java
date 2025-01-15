package com.trips.bookingservice.service.impl;

import com.trips.bookingservice.data.dto.PaymentRequestDto;
import com.trips.bookingservice.data.dto.PaymentResponseDto;
import com.trips.bookingservice.exception.BookingServiceRuntimeException;
import com.trips.bookingservice.feign.clients.PaymentServiceClient;
import com.trips.bookingservice.service.PaymentService;
import com.trips.bookingservice.utils.EurekaClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final EurekaClientUtil eurekaClientUtil;
    private final PaymentServiceClient paymentServiceClient;

    @Value("${services.payment-service.id}")
    String paymentServiceID;

    @Value("${services.payment-service.v1.api}")
    String paymentServiceV1;

    @Value("${services.payment-service.v1.name}")
    String paymentServiceV1Name;


    @Override
    public PaymentResponseDto createPaymentOrder(PaymentRequestDto paymentRequestDto) {
        log.info("Calling {} Service for creating payment order: {}", paymentServiceV1Name, paymentRequestDto);

        PaymentResponseDto paymentOrder = null;

        // Construct URL
        String paymentServiceUri = eurekaClientUtil.getServiceUri(paymentServiceID);
        String url = paymentServiceUri + paymentServiceV1 + "/orders";

        try {
            String paymentResponse = paymentServiceClient.makePayment().getBody();

            log.info("Payment Order created successfully: {}", paymentResponse);

//            paymentOrder = paymentModel;

        } catch (Exception ex) {
            log.error("Some error curred: {}", ex.getMessage());
            throw new BookingServiceRuntimeException(ex.getMessage());
        }

        return paymentOrder;
    }
}