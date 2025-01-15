package com.trips.paymentservice.services.impl;

import com.trips.paymentservice.constants.ApiConstants;
import com.trips.paymentservice.data.dto.StripePaymentOrderRequestModel;
import com.trips.paymentservice.data.entity.PaymentDetails;
import com.trips.paymentservice.data.mapper.StripePaymentServiceMapper;
import com.trips.paymentservice.exception.PaymentServiceRuntimeException;
import com.trips.paymentservice.repository.PaymentDetailsRepository;
import com.trips.paymentservice.services.PaymentDetailsService;
import com.stripe.model.checkout.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentDetailsServiceImpl implements PaymentDetailsService {
    @Autowired
    PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    StripePaymentServiceMapper stripePaymentServiceMapper;


    @Override
    public PaymentDetails savePayment(Session session, StripePaymentOrderRequestModel stripePaymentOrderRequestModel) {
        PaymentDetails paymentDetails = stripePaymentServiceMapper.toPaymentDetails(session, stripePaymentOrderRequestModel);
        try {
            return paymentDetailsRepository.save(paymentDetails);
        }catch (Exception ex){
            log.error("Some error curred: {}", ex.getMessage());
            throw new PaymentServiceRuntimeException(ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
    }
}