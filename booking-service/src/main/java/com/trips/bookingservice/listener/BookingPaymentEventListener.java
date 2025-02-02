package com.trips.bookingservice.listener;

import com.trips.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookingPaymentEventListener {
    private final BookingService bookingService;

    @KafkaListener(topics = "booking.payment")
    public void listen(final String payload) {
        log.info("Received booking payment event (kafka topic: 'booking.payment') with payload:{}", payload);
        try {
            JSONObject jsonPayload = new JSONObject(payload);
            String bookingId = jsonPayload.getString("bookingId");
            String status = jsonPayload.getString("status");
            bookingService.updateBookingStatusAndBookingPaymentMetaDataModel(bookingId, status);
        } catch (JSONException jsonException) {
            log.error("Invalid message received: {}", jsonException.getMessage());
        } catch (Exception ex) {
            log.error("Some error occurred: {}", ex.getMessage());
        }
    }
}