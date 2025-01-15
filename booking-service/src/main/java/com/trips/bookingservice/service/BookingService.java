package com.trips.bookingservice.service;


import com.trips.bookingservice.data.model.*;
import com.trips.bookingservice.data.model.query.GetAllBookingsQuery;
import com.trips.bookingservice.data.entity.BookingEntity;
import org.springframework.data.domain.Page;

public interface BookingService {
    BookingResponseModel createBookingOrder(CreateBookingRequestModel createBookingRequestModel);

    BookingResponseModel getBookingDataById(String bookingId);

    void handleBookingPaymentCancel(String bookingId);

    void updateBookingStatusAndBookingPaymentMetaDataModel(String bookingId, String status);

    Page<BookingEntity> getBookings(GetAllBookingsQuery criteria);

    BookingResponseModel updateBooking(String bookingId, UpdateBookingRequestModel updateBookingRequestModel);

    CancelBookingResponseModel cancelBooking(String bookingId, CancelBookingRequestModel cancelBookingRequestModel);
}