package com.trips.paymentservice.services.impl;

import com.trips.paymentservice.constants.BookingStatus;
import com.trips.paymentservice.data.dto.BookingDataResponseDto;
import com.trips.paymentservice.data.dto.UpdateBusStatusPayload;
import com.trips.paymentservice.exception.PaymentServiceRuntimeException;
import com.trips.paymentservice.repository.PaymentDetailsRepository;
import com.trips.paymentservice.services.*;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StripePaymentEventServiceImpl implements StripePaymentEventService {
    @Autowired
    PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    BookingService bookingService;

    @Autowired
    BusService busService;

    @Autowired
    KafkaMessageService kafkaMessageService;


    @Override
    public void handlePaymentCompletedUpdatedEvent(StripeObject stripeObject) {
        log.info("Received checkout.session.completed event with data:{}", stripeObject);
        Session sessionObj = (Session) stripeObject;

        String paymentId = sessionObj.getId();
        String status = sessionObj.getStatus();
        String bookingId = sessionObj.getClientReferenceId();

        try {
            log.info("Updating payment status");
            paymentDetailsRepository.updateStatusByPaymentId(paymentId, status);
            log.info("Updated payment status");

            updateRoomStatus(bookingId);
        }catch (Exception ex){
            log.error("Some error occurred while updating the payment status: {}", ex.getMessage());
            throw new PaymentServiceRuntimeException(ex.getMessage());
        }

        triggerBookingPaymentCompletedEvent(bookingId, status);
    }

    private void triggerBookingPaymentCompletedEvent(String bookingId, String status) {
        log.info("triggering booking payment completed event (kafka topic: 'booking.payment' & 'booking.mail')");
        JSONObject messagePayload = new JSONObject();
        messagePayload.put("bookingId", bookingId);
        messagePayload.put("status", status);
        kafkaMessageService.sendMessage("booking.payment", messagePayload.toString());
        kafkaMessageService.sendMessage("booking.mail", messagePayload.toString());
    }

    private void updateRoomStatus(String bookingId){
        log.info("Updating room status");
        BookingDataResponseDto bookingDataResponseDto = bookingService.getBookingDetails(bookingId);

        UpdateBusStatusPayload updateBusStatusPayload = UpdateBusStatusPayload.builder()
                .id(bookingDataResponseDto.getBusDetails().getId())
                .bookingStatus(BookingStatus.OCCUPIED)
                .uid(bookingDataResponseDto.getUid())
                .build();

        busService.updateBusStatus(updateBusStatusPayload);

        log.info("Updated the room status");
    }
}