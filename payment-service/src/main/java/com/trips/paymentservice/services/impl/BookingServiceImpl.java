package com.trips.paymentservice.services.impl;

import com.trips.paymentservice.data.dto.BookingDataResponseDto;
import com.trips.paymentservice.exception.PaymentServiceRuntimeException;
import com.trips.paymentservice.feign.clients.BookingServiceClient;
import com.trips.paymentservice.services.BookingService;
import com.trips.paymentservice.util.EurekaClientUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class BookingServiceImpl implements BookingService {
    @Autowired
    EurekaClientUtil eurekaClientUtil;

    @Autowired
    BookingServiceClient bookingServiceClient;

    @Value("${services.booking-service.id}")
    String bookingServiceID;

    @Value("${services.booking-service.v1.api}")
    String bookingServiceV1;

    @Value("${services.booking-service.v1.name}")
    String bookingServiceV1Name;


    @Override
    public BookingDataResponseDto getBookingDetails(String bookingId) {
        log.info("calling Booking Service for getting booking details");

        BookingDataResponseDto bookingDataResponseDto = null;

        // Construct URL
        String bookingServiceUri = eurekaClientUtil.getServiceUri(bookingServiceID);
        String url = bookingServiceUri + bookingServiceV1 + "/" + bookingId;

        try {
            BookingDataResponseDto response = bookingServiceClient.getBookingDetails(bookingId);

            log.info("{} response: {}", bookingServiceV1Name, response);

            bookingDataResponseDto = response;
        } catch (FeignException.FeignClientException feignClientException) {
            handleWebClientException(feignClientException);
        } catch (Exception ex) {
            log.error("Some error curred: {}", ex.getMessage());
            throw new PaymentServiceRuntimeException(ex.getMessage());
        }

        return bookingDataResponseDto;
    }

    private void handleWebClientException(FeignException.FeignClientException feignClientException) {
        log.info("Handling WebClientException");

        // Convert error response to JSONObject
        JSONObject response = new JSONObject(feignClientException.getMessage());

        log.info("Error response from {} Service is: {}", bookingServiceV1Name, response);

        String errorCode = response.getString("errorCode");
        String message = response.getString("message");
        String details = response.has("details") ? response.getString("details") : "";

        throw new PaymentServiceRuntimeException(message);
    }
}
