package com.trips.paymentservice.services;

import com.trips.paymentservice.data.model.*;
import com.trips.paymentservice.data.model.dashboard.PaymentStatisticsResponseModel;
import com.trips.paymentservice.data.model.dashboard.RevenueStatisticsResponseModel;

import java.math.BigDecimal;

public interface PaymentService {
    PaymentOrderResponseModel createOrder(PaymentOrderRequestModel paymentOrderRequestModel);

    PaymentOrderSuccessResponse handlePaymentOrderSuccess(PaymentOrderSuccessRequest paymentOrderSuccessRequest);

    public Long getApplicationFeeAmount(BigDecimal amount);

    String getSuccessUrl(String bookingId);

    public String getCancelUrl(String bookingId);

    PaymentDetailsResponseModel getPaymentDetailsByBookingId(String bookingId);

    PaymentStatisticsResponseModel getBookingStatistics(String organizationId, String startDate, String endDate);

    RevenueStatisticsResponseModel getRevenueStatistics(String organizationId, String duration);
}