package com.trips.paymentservice.data.dto;

import com.trips.paymentservice.constants.BookingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateBusStatusPayload {
    private String id;

    private String uid;

    BookingStatus bookingStatus;
}
