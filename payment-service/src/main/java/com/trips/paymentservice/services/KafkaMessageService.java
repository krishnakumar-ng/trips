package com.trips.paymentservice.services;

public interface KafkaMessageService {
    void sendMessage(String topic, String message);
}
