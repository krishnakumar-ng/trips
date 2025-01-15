package com.trips.bookingservice.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.bookingservice.data.model.metadata.BusMetaDataModel;
import com.trips.bookingservice.enums.BookingStatus;
import com.trips.bookingservice.enums.OnlinePaymentMethodType;
import com.trips.bookingservice.enums.PaymentType;
import com.trips.bookingservice.data.dto.PassengerDetailsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseModel {

    @Schema(description = "booking request id", example = "bk_req_kjhabsd78w3jba78s", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "bus id", example = "room_kjansdf8u3wk976", requiredMode = Schema.RequiredMode.REQUIRED)
    private String busId;

    private BusMetaDataModel busDetails;

    @Schema(description = "user id", example = "901237412412", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @Schema(description = "contact details")
    private ContactDetails contactDetails;

    @Schema(description = "boarding-date", example = "2023-12-24T06:21:38.699Z", requiredMode = Schema.RequiredMode.REQUIRED)
    private String boardingDate;

    @Schema(description = "guests", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<PassengerDetailsDto> passengerDetailsList;

    @Schema(description = "booking request status", example = "PENDING", requiredMode = Schema.RequiredMode.REQUIRED)
    private BookingStatus status;

    @Schema(description = "amount", example = "1000.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

    private String paymentOrderId;

    @Schema(description = "payment type", example = "ONLINE_PAYMENT", requiredMode = Schema.RequiredMode.REQUIRED)
    private PaymentType paymentType;

    @Schema(description = "payment method type", example = "STRIPE", requiredMode = Schema.RequiredMode.REQUIRED)
    private OnlinePaymentMethodType onlinePaymentMethodType;

    private String paymentUrl;

    @Schema(description = "booking date", example = "date", requiredMode = Schema.RequiredMode.REQUIRED)
    private ZonedDateTime bookingDate;

    @Schema(description = "created date", example = "date", requiredMode = Schema.RequiredMode.REQUIRED)
    private ZonedDateTime createdDate;

    @Schema(description = "last update date", example = "date", requiredMode = Schema.RequiredMode.REQUIRED)
    private ZonedDateTime lastUpdateDate;
}