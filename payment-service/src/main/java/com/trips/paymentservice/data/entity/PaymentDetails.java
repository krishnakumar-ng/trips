package com.trips.paymentservice.data.entity;

import com.trips.paymentservice.constants.PaymentMethodType;
import com.trips.paymentservice.util.generator.CustomPaymentIdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
@Table(name = "payments")
@Data
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CustomPaymentIdGenerator
    String id;

    @Column(name = "paymentId", nullable = false)
    private String paymentId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentMethodType type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    String currency;

    @Column(name = "bookingId", nullable = false)
    private String bookingId;

    @Column(name = "uid", nullable = false)
    String uid;

    @Column(name = "status", nullable = false)
    String status;

    @CreatedDate
    @Column(name = "createdAt")
    private ZonedDateTime createdAt;

    @LastModifiedBy
    @Column(name = "updatedAt", nullable = false)
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) {
            createdAt = ZonedDateTime.now();
        }
        if (this.updatedAt == null) {
            updatedAt = ZonedDateTime.now();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }
}