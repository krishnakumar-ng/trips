package com.trips.bookingservice.data.model.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trips.bookingservice.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllBookingsQuery {
    @Schema(description = "id", example = "bk_akjn7883nasd", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "uid", example = "IbW3MtSX6GM35xaYk8PV4HxsEP72", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @Schema(description = "bookingDate", example = """
            {
                "operator": "EQUAL",
                "bookingDate": "2024-01-28T16:53:15.708706Z"
            }""", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BookingDateRequestModel bookingDate;

    @Schema(description = "checkInDate", example = """
            {
                "operator": "EQUAL",
                "checkInDate": "2024-01-28T16:53:15.708706Z"
            }""", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BoardingDateRequestModel boardingDate;

    @Schema(description = "amount", example = """
            {
                "operator": "EQUAL",
                "amount": "1200"
            }""", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private AmountRequestModel amount;

    @Schema(description = "booking status", example = "CONFIRMED", requiredMode = Schema.RequiredMode.REQUIRED)
    private BookingStatus status;

    @Schema(description = "page", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer page;

    @Schema(description = "pageSize", example = "20", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageSize;
}
