package com.trips.bookingservice.api.controller;

import com.trips.bookingservice.data.dto.BusDetailsDto;
import com.trips.bookingservice.data.model.*;
import com.trips.bookingservice.data.model.metadata.BusMetaDataModel;
import com.trips.bookingservice.data.model.metadata.PaymentMetaDataModel;
import com.trips.bookingservice.data.model.query.GetAllBookingsQuery;
import com.trips.bookingservice.api.resource.BookingResource;
import com.trips.bookingservice.data.entity.BookingEntity;
import com.trips.bookingservice.enums.BookingStatus;
import com.trips.bookingservice.exception.BookingServiceRuntimeException;
import com.trips.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.time.ZonedDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookingController implements BookingResource {

    private final BookingService bookingService;

    @Override
    public ResponseEntity<BookingResponseModel> createBooking(CreateBookingRequestModel createBookingRequestModel) {
        log.info("Creating booking for user : {}", createBookingRequestModel.getUserId());
        BookingResponseModel bookingOrderResponseModel = bookingService.createBookingOrder(createBookingRequestModel);
        log.info("Created Booking order: {}", bookingOrderResponseModel);
        return new ResponseEntity<>(bookingOrderResponseModel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookingResponseModel> updateBooking(String bookingId, UpdateBookingRequestModel updateBookingRequestModel) {
        log.info("Updating booking details for bookingId: {}", bookingId);
        BookingResponseModel bookingDataResponseModel = bookingService.updateBooking(bookingId, updateBookingRequestModel);
        log.info("Updated the booking: {}", bookingDataResponseModel);
        return new ResponseEntity<>(bookingDataResponseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CancelBookingResponseModel> cancelBooking(String bookingId, CancelBookingRequestModel cancelBookingRequestModel) {
        log.info("Cancelling booking for bookingId: {}", bookingId);
        CancelBookingResponseModel cancelBookingResponseModel = bookingService.cancelBooking(bookingId, cancelBookingRequestModel);
        log.info("Cancelled the booking: {}", cancelBookingResponseModel);
        return new ResponseEntity<>(cancelBookingResponseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<BookingEntity>> getBookings(GetAllBookingsQuery query) {
        log.info("Fetching booking data");
        Page<BookingEntity> bookings = bookingService.getBookings(query);
        log.info("Fetched the booking data : {}", bookings);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingResponseModel> getBookingData(String bookingId) {
        log.info("Fetching booking data for booking id: {}", bookingId);
        BookingResponseModel bookingDataResponseModel = bookingService.getBookingDataById(bookingId);
        log.info("Fetched the booking data : {}", bookingDataResponseModel);
        return new ResponseEntity<>(bookingDataResponseModel, HttpStatus.OK);
    }

    @Override
    public RedirectView handleBookingPaymentSuccess(String bookingId) {
        log.info("Received booking payment success request");
        return new RedirectView("http://localhost:3000/bookings/" + bookingId);
    }

    @Override
    public RedirectView handleBookingPaymentCancel(String bookingId) {
        log.info("Received booking payment cancel request");
        bookingService.handleBookingPaymentCancel(bookingId);
        return new RedirectView("http://localhost:3000");
    }
}
