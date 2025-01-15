package com.trips.bookingservice.data.dto;

import com.trips.bookingservice.data.model.ContactDetails;
import com.trips.bookingservice.enums.OnlinePaymentMethodType;
import com.trips.bookingservice.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {
    private String busId;
    private String operatorId;
    private String boardingDate;
    private String userId;
    private ContactDetails contactDetails;
    private List<PassengerDetailsDto> passengerDetailsList;
    private PaymentType paymentType;
    private OnlinePaymentMethodType onlinePaymentMethodType;
}
