package com.trips.paymentservice.repository;

import com.trips.paymentservice.data.entity.PaymentDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, String> {
    @Modifying
    @Query("update PaymentDetails paymentDetails "+
            "set paymentDetails.status=:status "+
            "where paymentDetails.paymentId=:paymentId")
    void updateStatusByPaymentId(@Param("paymentId") String paymentId, @Param("status") String status);

    Optional<PaymentDetails> findByBookingId(String bookingId);

    @Query("SELECT SUM(p.amount) FROM PaymentDetails p "+
            "WHERE p.operatorId=:operatorId")
    BigDecimal getTotalAmountByOrganizationId(@Param("operatorId") String organizationId);

    @Query("SELECT SUM(p.amount) FROM PaymentDetails p WHERE p.operatorId = :operatorId AND p.createdAt BETWEEN :startDate AND :endDate")
    BigDecimal sumAmountByOrganizationIdAndDateRange(@Param("operatorId") String organizationId, @Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

    @Query("SELECT DATE(p.createdAt), SUM(p.amount) FROM PaymentDetails p WHERE p.operatorId = :operatorId AND p.createdAt BETWEEN :startDate AND :endDate GROUP BY DATE(p.createdAt)")
    List<Object[]> sumAmountByOrganizationIdAndDateRangeGroupByDate(@Param("operatorId") String organizationId, @Param("startDate") Instant startDate, @Param("endDate") Instant endDate);
}