package com.trips.bookingservice.listener;

import com.trips.bookingservice.event.BookingEvent;
import com.trips.bookingservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookingEventListener {
    private KafkaMessageService kafkaMessageService;

    @EventListener
    public void handleBookingEvent(BookingEvent bookingEvent){
        switch (bookingEvent.getEventType()) {
            case CREATED -> {
                // Handle created event
                // TODO: Send message to Kafka for created event
            }
            case UPDATED -> {
                // Handle updated event
                // TODO: Send message to Kafka for updated event
            }
            case CANCELLED -> {
                // Handle deleted event
                // TODO: Send message to Kafka for canceled event
            }
            default -> {
                // Handle other event types if needed
            }
        }
    }
}