package com.trips.paymentservice.services.impl;

import com.trips.paymentservice.data.dto.*;
import com.trips.paymentservice.data.entity.PaymentDetails;
import com.trips.paymentservice.data.mapper.StripePaymentServiceMapper;
import com.trips.paymentservice.data.mapper.StripeServiceMapper;
import com.trips.paymentservice.exception.*;
import com.trips.paymentservice.services.PaymentDetailsService;
import com.trips.paymentservice.services.StripePaymentService;
import com.trips.paymentservice.services.StripePaymentValidationService;
import com.trips.paymentservice.services.StripeService;
import com.stripe.model.checkout.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StripePaymentServiceImpl implements StripePaymentService {
    @Autowired
    StripeService stripeService;

    @Autowired
    StripeServiceMapper stripeServiceMapper;

    @Autowired
    StripePaymentValidationService stripePaymentValidationService;

    @Autowired
    StripePaymentServiceMapper stripePaymentServiceMapper;

    @Autowired
    PaymentDetailsService paymentDetailsService;


    @Override
    public StripePaymentOrderResponseModel createOrder(StripePaymentOrderRequestModel stripePaymentOrderRequestModel) {
        log.info("Creating stripe payment order(Stripe Session) for: {}", stripePaymentOrderRequestModel);
        StripePaymentOrder stripePaymentOrder = validatePaymentOrderRequestAndGetStripePaymentOrder(stripePaymentOrderRequestModel);
        StripePaymentOrderResponseModel stripePaymentOrderResponseModel = stripePaymentServiceMapper.toStripePaymentOrderResponseModel(stripePaymentOrder);
        log.info("Created stripe payment order: {}", stripePaymentOrderResponseModel);
        return stripePaymentOrderResponseModel;
    }

    @Override
    public StripePaymentOrderSuccessResponse handlePaymentOrderSuccess(StripePaymentOrderSuccessRequest stripePaymentOrderSuccessRequest) {
        verifyPaymentSignature(stripePaymentOrderSuccessRequest);
        StripePaymentOrderSuccessResponse stripePaymentOrderSuccessResponse = StripePaymentOrderSuccessResponse.builder()
                .message("Payment successfully!")
                .build();
        return stripePaymentOrderSuccessResponse;
    }

    private void verifyPaymentSignature(StripePaymentOrderSuccessRequest stripePaymentOrderSuccessRequest){
        log.info("Verifying stripe payment success");

    }

    private StripePaymentOrder validatePaymentOrderRequestAndGetStripePaymentOrder(StripePaymentOrderRequestModel stripePaymentOrderRequestModel) {
        log.info("Validating PaymentOrderRequest");
        if(stripePaymentValidationService.validateOrganizationAccountActive(stripePaymentOrderRequestModel.getOrganizationId())){
            log.info("Validated PaymentOrderRequest");
            StripePaymentOrder stripePaymentOrder = createStripePaymentOrder(stripePaymentOrderRequestModel);
            return stripePaymentOrder;
        }else{
            log.error("Organization account is not active");
            throw new PaymentServiceRuntimeException("Payment processing is currently unavailable as the organization's account is inactive.");
        }
    }

    private StripePaymentOrder createStripePaymentOrder(StripePaymentOrderRequestModel stripePaymentOrderRequestModel) {
        log.info("Create Stripe Payment Orders");
        StripeCreateSessionObjectDto stripeCreateSessionObjectDto = stripePaymentServiceMapper.toStripeCreateSessionObjectDto(stripePaymentOrderRequestModel);
        Session session = stripeService.createSession(stripeCreateSessionObjectDto);
        log.info("Created Stripe Payment order: {}", session);

        log.info("Saving payment details");
        PaymentDetails paymentDetails = paymentDetailsService.savePayment(session, stripePaymentOrderRequestModel);
        log.info("Saved payment details: {}", paymentDetails);

        return stripeServiceMapper.toStripePaymentOrder(session, stripePaymentOrderRequestModel);
    }
}