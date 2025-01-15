package com.trips.bookingservice.event;

import com.trips.bookingservice.data.entity.BookingEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookingEvent extends ApplicationEvent {

    public enum EventType{
        CREATED, UPDATED, CANCELLED
    }

    private final BookingEntity bookingEntity;
    private final EventType eventType;

    public BookingEvent(Object source, EventType eventType, BookingEntity bookingEntity) {
        super(source);
        this.eventType = eventType;
        this.bookingEntity = bookingEntity;
    }
}