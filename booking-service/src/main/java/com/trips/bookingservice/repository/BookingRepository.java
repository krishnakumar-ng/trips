package com.trips.bookingservice.repository;

import com.trips.bookingservice.data.entity.BookingEntity;
import com.trips.bookingservice.data.model.metadata.PaymentMetaDataModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    @Modifying
    @Query("update BookingEntity bookingDetails "+
            "set bookingDetails.paymentMetaDataModel=:paymentMetaDataModel "+
            "where bookingDetails.id=:id")
    void updatePaymentMetaDataModelById(@Param("id") String id, @Param("paymentMetaDataModel") PaymentMetaDataModel paymentMetaDataModel);

    Page<BookingEntity> findAll(Specification<BookingEntity> specification, Pageable pageable);

    Page<BookingEntity> findByIdContaining(String idContains, Pageable pageable);
}