package com.trips.bookingservice.service;

public interface KafkaMessageService {
    void sendMessage(String topic, String message);
}