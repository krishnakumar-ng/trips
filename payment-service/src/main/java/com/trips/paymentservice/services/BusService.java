package com.trips.paymentservice.services;

import com.trips.paymentservice.data.dto.UpdateBusStatusPayload;

public interface BusService {
    void updateBusStatus(UpdateBusStatusPayload updateBusStatusPayload);
}