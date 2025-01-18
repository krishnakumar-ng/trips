package com.trips.bookingservice.data.entity;

import com.trips.bookingservice.data.model.ContactDetails;
import com.trips.bookingservice.enums.BookingStatus;
import com.trips.bookingservice.enums.PaymentType;
import com.trips.bookingservice.data.dto.PassengerDetailsDto;
import com.trips.bookingservice.data.model.metadata.PaymentMetaDataModel;
import com.trips.bookingservice.data.model.metadata.BusMetaDataModel;
import com.trips.bookingservice.utils.generator.CustomBookingIdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CustomBookingIdGenerator
    private String id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "boarding_date", nullable = false)
    private LocalDate boardingDate;

    @Column(name = "bus_details", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private BusMetaDataModel busDetails;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "contact_details")
    @JdbcTypeCode(SqlTypes.JSON)
    private ContactDetails contactDetails;

    @Column(name = "passengers", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<PassengerDetailsDto> passengerDetailsList;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "payment_meta_data_model")
    @JdbcTypeCode(SqlTypes.JSON)
    private PaymentMetaDataModel paymentMetaDataModel;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "booking_date", nullable = false)
    private ZonedDateTime bookingDate;

    @CreatedDate
    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @LastModifiedBy
    @Column(name = "last_update_date", nullable = false)
    private ZonedDateTime lastUpdateDate;

    @PrePersist
    protected void prePersist() {
        if (this.createdDate == null) {
            createdDate = ZonedDateTime.now();
        }
        if (this.lastUpdateDate == null) {
            lastUpdateDate = ZonedDateTime.now();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.lastUpdateDate = ZonedDateTime.now();
    }
}