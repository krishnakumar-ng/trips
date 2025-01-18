package com.trips.bookingservice.service.impl;

import com.trips.bookingservice.data.model.*;
import com.trips.bookingservice.data.model.metadata.BusMetaDataModel;
import com.trips.bookingservice.data.model.metadata.PaymentMetaDataModel;
import com.trips.bookingservice.data.model.query.GetAllBookingsQuery;
import com.trips.bookingservice.constants.*;
import com.trips.bookingservice.data.dto.*;
import com.trips.bookingservice.data.entity.BookingEntity;
import com.trips.bookingservice.data.mapper.BookingMapper;
import com.trips.bookingservice.data.mapper.BusMapper;
import com.trips.bookingservice.enums.*;
import com.trips.bookingservice.exception.*;
import com.trips.bookingservice.repository.BookingRepository;
import com.trips.bookingservice.service.*;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BusService busService;
    private final PaymentService paymentService;
    private final BookingMapper bookingMapper;
    private final BusMapper busMapper;
    private final BookingRepository bookingRepository;
    private final BookingValidationService bookingValidationService;

    @Override
    public BookingResponseModel createBookingOrder(CreateBookingRequestModel bookingRequestModel) {
        GenericValidator validation = bookingValidationService.validateBookingRequestData(bookingRequestModel);

        if (validation.equals(GenericValidator.VALID)) {
            BookingResponseModel bookingResponseModel = null;

            BookingEntity bookingEntity = createBookingRecord(bookingRequestModel);

            PaymentType type = bookingRequestModel.getPaymentType();

            switch (type) {
                case ONLINE_PAYMENT -> {
                    log.info("Redirecting to {} for payment as the payment type is: {}", type, type);
//                    PaymentResponseDto onlinePaymentOrderResponseDto = doOnlinePayment(bookingEntity);
                    PaymentResponseDto onlinePaymentOrderResponseDto = PaymentResponseDto.builder().build();
                    bookingResponseModel = bookingMapper.toBookingResponseModel(onlinePaymentOrderResponseDto, bookingEntity);
                }
                case CASH_PAYMENT -> {
//                    TODO: Handle Cash Payment
                }
                default -> {
                    log.info("Booking cannot be processed due to an invalid payment type: {}", type);
                    throw new InvalidPaymentTypeException(ApiConstants.INVALID_PAYMENT_TYPE, "Invalid payment type", "");
                }
            }
            log.info("Booking Order: {}", bookingResponseModel);
            return bookingResponseModel;
        } else {
            log.error("Invalid Booking Request: {}", bookingRequestModel);
            throw new InvalidBookingRequestDataException(ApiConstants.INVALID_BOOKING_REQUEST_DATA, "Booking cannot be processed due to an invalid booking request data", "");
        }
    }

    @Override
    public BookingResponseModel getBookingDataById(String bookingId) {
        log.info("Fetching the booking data for booking id:{}", bookingId);
        Optional<BookingEntity> bookings = bookingRepository.findById(bookingId);
        if (bookings.isEmpty()) {
            log.error("Could not find any booking for id:{}", bookingId);
            throw new BookingNotFoundException(ApiConstants.NOT_FOUND, "Could not find any booking for booking id:" + bookingId, null);
        }
        log.info("booking data for bookingId:{} is :{}", bookings, bookings.get());
        return bookingMapper.toBookingResponseModel(bookings.get());
    }

    @Override
    public void handleBookingPaymentCancel(String bookingId) {
        log.info("handling booking payment cancel for bookingId:{}", bookingId);
        // TODO: Delete the booking request and booking record
    }

    @Override
    public void updateBookingStatusAndBookingPaymentMetaDataModel(String bookingId, String status) {
        log.info("Updating booking status and paymentMetaData for bookingId: {} ", bookingId);
        BookingEntity bookingEntity = getBookingDetailsEntityById(bookingId);
        if ("complete".equals(status)) {
            bookingEntity.setStatus(BookingStatus.CONFIRMED);
            StripePaymentServiceProviderModel stripePaymentServiceProviderModel = bookingEntity.getPaymentMetaDataModel().getStripePaymentServiceProviderModel();
            stripePaymentServiceProviderModel.setStatus(StripePaymentStatus.COMPLETE);

            BookingEntity savedBookingEntity = bookingRepository.save(bookingEntity);
            log.info("Updated booking status and paymentMetaData: {}", savedBookingEntity);
        }
    }

    @Override
    public Page<BookingEntity> getBookings(GetAllBookingsQuery criteria) {
        int page = criteria.getPage() == null ? 0 : criteria.getPage();
        int size = criteria.getPageSize() == null ? 10 : criteria.getPageSize();

        Pageable pr = PageRequest.of(page, size);

        Specification<BookingEntity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getId() != null) {
                predicates.add(cb.like(root.get("id"), "%" + criteria.getId() + "%"));
            }

            if (criteria.getUserId() != null) {
                predicates.add(cb.like(root.get("uid"), criteria.getUserId()));
            }

            if (criteria.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), criteria.getStatus()));
            }

            if (criteria.getAmount() != null && criteria.getAmount().getSortingType().equals(SortingType.ASC)) {
                query.orderBy(cb.asc(root.get("amount")));
            } else if (criteria.getAmount() != null && criteria.getAmount().getSortingType().equals(SortingType.DESC)) {
                query.orderBy(cb.desc(root.get("amount")));
            }

            if (criteria.getBookingDate() != null && criteria.getBookingDate().getSortingType().equals(SortingType.ASC)) {
                query.orderBy(cb.asc(root.get("bookingDate")));
            } else if (criteria.getBookingDate() != null && criteria.getBookingDate().getSortingType().equals(SortingType.DESC)) {
                query.orderBy(cb.desc(root.get("bookingDate")));
            }

            if (criteria.getBoardingDate() != null && criteria.getBoardingDate().getSortingType().equals(SortingType.ASC)) {
                query.orderBy(cb.asc(root.get("boardingDate")));
            } else if (criteria.getBoardingDate() != null && criteria.getBoardingDate().getSortingType().equals(SortingType.DESC)) {
                query.orderBy(cb.desc(root.get("boardingDate")));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return bookingRepository.findAll(spec, pr);
    }

    @Override
    public BookingResponseModel updateBooking(String bookingId, UpdateBookingRequestModel updateBookingRequestModel) {
        log.info("Updating the booking details for bookingId: {} with data:{}", bookingId, updateBookingRequestModel);

        log.info("Fetching the previous data");
        Optional<BookingEntity> bookingDetails = bookingRepository.findById(bookingId);
        if (bookingDetails.isEmpty()) {
            log.error("Could not find any booking for bookingId:{}", bookingId);
            throw new BookingNotFoundException(ApiConstants.NOT_FOUND, "Could not find any booking for bookingId: " + bookingId, null);
        }
        log.info("Fetched booking data:{}", bookingDetails.get());

        BookingEntity booking = bookingDetails.get();

        try {
            BookingEntity updatedBookingData = updateBookingData(booking, updateBookingRequestModel);
            BookingEntity savedData = bookingRepository.save(updatedBookingData);
            log.info("Updated booking: {}", savedData);
            return bookingMapper.toBookingResponseModel(savedData);
        } catch (Exception ex) {
            log.info("Some error occurred while updating the booking details: {}", ex.getMessage());
            throw new BookingServiceRuntimeException(ex.getMessage());
        }
    }

    @Override
    public CancelBookingResponseModel cancelBooking(String bookingId, CancelBookingRequestModel cancelBookingRequestModel) {
        log.info("Cancelling the booking with bookingId: {}", bookingId);

        log.info("Fetching the previous data");
        Optional<BookingEntity> bookingDetails = bookingRepository.findById(bookingId);
        if (bookingDetails.isEmpty()) {
            log.error("Could not find any booking for bookingId:{}", bookingId);
            throw new BookingNotFoundException(ApiConstants.NOT_FOUND, "Could not find any booking for bookingId: " + bookingId, null);
        }
        log.info("Fetched booking data:{}", bookingDetails.get());

        BookingEntity booking = bookingDetails.get();

        try {
            BookingEntity updatedBookingStatus = updatedBookingStatus(booking, BookingStatus.CANCELLED);
            BookingEntity savedData = bookingRepository.save(updatedBookingStatus);
            log.info("Updated booking: {}", savedData);

            return CancelBookingResponseModel.builder()
                    .success(Boolean.TRUE)
                    .message("Booking Cancelled successfully for booking id - "+bookingId)
                    .build();

        } catch (Exception ex) {
            log.info("Some error occurred while updating the booking details: {}", ex.getMessage());
            throw new BookingServiceRuntimeException(ex.getMessage());
        }
    }


    private BookingEntity getBookingDetailsEntityById(String id) {
        log.info("Fetching booking data for bookingId:{}", id);
        Optional<BookingEntity> bookingDetails = bookingRepository.findById(id);
        if (bookingDetails.isEmpty()) {
            log.error("Could not find any booking for bookingId:{}", id);
            throw new BookingNotFoundException(ApiConstants.NOT_FOUND, "Could not find any booking for bookingId:" + id, null);
        }
        log.info("Fetched booking data:{}", bookingDetails.get());
        return bookingDetails.get();
    }

    private BookingEntity updateBookingData(BookingEntity bookingEntity, UpdateBookingRequestModel updateBookingRequestModel) {
        log.info("Updating booking data to : {}", updateBookingRequestModel);
        bookingEntity.setBoardingDate(LocalDate.parse(updateBookingRequestModel.getBoardingDate()));
        return bookingEntity;
    }

    private BookingEntity updatedBookingStatus(BookingEntity bookingEntity, BookingStatus bookingStatus) {
        log.info("Changing status to : {}", bookingStatus);
        bookingEntity.setStatus(bookingStatus);
        return bookingEntity;
    }

    private PaymentResponseDto doOnlinePayment(BookingEntity bookingEntity) {
        log.info("Creating booking payment order.");
        switch (bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType()) {
            case STRIPE -> {
                log.info("Redirecting to {} payment service provider for payment order , as the payment method is: {}",
                        bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType(),
                        bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType()
                );
                StripePaymentOrderResponseDto stripePaymentOrderResponseDto = createStripePaymentOrder(bookingEntity);
                PaymentResponseDto onlinePaymentOrderResponseDto = bookingMapper.toOnlinePaymentOrderResponseDto(stripePaymentOrderResponseDto);
                log.info("Created booking payment order: {}", onlinePaymentOrderResponseDto);
                return onlinePaymentOrderResponseDto;
            }
            case UPI -> {
//                TODO: Integrate UPI Payment System
                return null;
            }
            default -> {
                log.info("Booking cannot be processed due to invalid payment method type: {}", bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType());
                throw new InvalidPaymentMethodTypeException(ApiConstants.INVALID_PAYMENT_METHOD_TYPE, "Invalid payment method type", "");
            }
        }
    }

    private StripePaymentOrderResponseDto createStripePaymentOrder(BookingEntity bookingEntity) {
        PaymentRequestDto paymentRequestDto = bookingMapper.toPaymentOrderRequestDto(bookingEntity);

        log.info("Creating {} payment order for: {}", bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType(), paymentRequestDto);
        PaymentResponseDto paymentResponseDto = paymentService.createPaymentOrder(paymentRequestDto);
        log.info("Created {} payment order: {}", bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType(), paymentResponseDto);

        updateBookingDetails(bookingEntity, paymentResponseDto);
        log.info("Updated bookingDetails with {} data", bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType());

        return bookingMapper.toStripePaymentOrderResponseDto(paymentResponseDto);
    }

    private void updateBookingDetails(BookingEntity bookingEntity, PaymentResponseDto paymentResponseDto) {
        log.info("Updating bookingDetails with {} Payment Service Provider data", bookingEntity.getPaymentMetaDataModel().getOnlinePaymentMethodType());
        StripePaymentServiceProviderModel stripePaymentServiceProviderModel = bookingMapper.toStripePaymentServiceProvider(paymentResponseDto);
        PaymentMetaDataModel paymentMetaDataModel = bookingEntity.getPaymentMetaDataModel();
        paymentMetaDataModel.setStripePaymentServiceProviderModel(stripePaymentServiceProviderModel);
        try {
            bookingRepository.updatePaymentMetaDataModelById(bookingEntity.getId(), paymentMetaDataModel);
            log.info("Updated booking details with payment service provider");
        } catch (Exception ex) {
            log.info("Some error occurred while updating the booking details: {}", ex.getMessage());
            throw new BookingServiceRuntimeException(ex.getMessage());
        }
    }

    private BookingEntity createBookingRecord(CreateBookingRequestModel createBookingRequestModel) {
        log.info("creating booking record");
        BusDetailsDto busDetails = busService.getBus(createBookingRequestModel.getBusId());

        String amount = busMapper.toAmount(busDetails);
        BusMetaDataModel busMetaDataModel = busMapper.toBusMetaDataModel(busDetails);
        PaymentMetaDataModel paymentMetaDataModel = busMapper.toBookingPaymentMetaDataModel(createBookingRequestModel.getOnlinePaymentMethodType(), amount, busMetaDataModel.getPrice().getCurrency(), null);
        BookingStatus status = BookingStatus.PAYMENT_PENDING;
        ZonedDateTime bookingDate = ZonedDateTime.now();

        try {
            BookingEntity bookingEntity = createBookingDetails(
                    createBookingRequestModel,
                    amount,
                    busMetaDataModel,
                    paymentMetaDataModel,
                    status,
                    bookingDate
            );

            bookingEntity = bookingRepository.save(bookingEntity);

            log.info("created booking record: {}", bookingEntity);

            return bookingEntity;
        } catch (Exception ex) {
            log.error("Some error curred: {}", ex.getMessage());
            throw new BookingServiceRuntimeException(ex.getMessage());
        }
    }

    private BookingEntity createBookingDetails(CreateBookingRequestModel createBookingRequestModel,
                                               String amount,
                                               BusMetaDataModel busDetailsMetaData,
                                               PaymentMetaDataModel paymentMetaDataModel,
                                               BookingStatus status,
                                               ZonedDateTime bookingDate) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBoardingDate(LocalDate.parse(createBookingRequestModel.getBoardingDate()));
        bookingEntity.setPassengerDetailsList(createBookingRequestModel.getPassengerDetailsList());
        bookingEntity.setContactDetails(createBookingRequestModel.getContactDetails());
        bookingEntity.setPaymentType(createBookingRequestModel.getPaymentType());
        bookingEntity.setUserId(createBookingRequestModel.getUserId());
        bookingEntity.setBookingDate(bookingDate);
        bookingEntity.setAmount(new BigDecimal(amount));
        bookingEntity.setBusDetails(busDetailsMetaData);
        bookingEntity.setPaymentMetaDataModel(paymentMetaDataModel);
        bookingEntity.setStatus(status);
        return bookingEntity;
    }
}