package com.trips.bookingservice.api.resource;

import com.trips.bookingservice.constants.ApiConstants;
import com.trips.bookingservice.data.model.*;
import com.trips.bookingservice.data.model.query.GetAllBookingsQuery;
import com.trips.bookingservice.data.entity.BookingEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import static com.trips.bookingservice.constants.ApiConstants.API;
import static com.trips.bookingservice.constants.ApiConstants.VERSION1;

@Tag(name = "booking service resource")
@RequestMapping("/" + API + "/" + VERSION1 + ApiConstants.BOOKING_SERVICE_ENDPOINT)
public interface BookingResource {
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Create Booking")
    @PostMapping()
    ResponseEntity<BookingResponseModel> createBooking(
            @Parameter(name = "bookingRequestId", description = "booking request id")
            @Schema(description = "Reference", example = "bk_req_8732njsf87yh", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestBody @Valid CreateBookingRequestModel createBookingRequestModel);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Update Booking")
    @PatchMapping("/update/{bookingId}")
    ResponseEntity<BookingResponseModel> updateBooking(
            @Parameter(name = "bookingId", description = "bookingId")
            @Schema(description = "Reference", example = "bk_8732njasdfasf87yh", requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable(name = "bookingId") String bookingId,
            @RequestBody @Valid UpdateBookingRequestModel updateBookingRequestModel);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Cancel Booking")
    @PatchMapping("/cancel/{bookingId}")
    ResponseEntity<CancelBookingResponseModel> cancelBooking(
            @Parameter(name = "bookingId", description = "bookingId")
            @Schema(description = "Reference", example = "bk_8732njasdfasf87yh", requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable(name = "bookingId") String bookingId,
            @RequestBody @Valid CancelBookingRequestModel cancelBookingRequestModel);


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Get Bookings")
    @PostMapping("/search")
    ResponseEntity<Page<BookingEntity>> getBookings(
            @RequestBody GetAllBookingsQuery query
    );

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "GET", summary = "Get Booking Data By Id")
    @GetMapping("/{bookingId}")
    ResponseEntity<BookingResponseModel> getBookingData(
            @Parameter(name = "bookingId", description = "booking Id")
            @Schema(description = "Reference", example = "bk_8732njsf87yh", requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable(name = "bookingId") String bookingId);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "GET", summary = "Called after payment is completed")
    @GetMapping("/{bookingId}/payment/success")
    RedirectView handleBookingPaymentSuccess(
            @Parameter(name = "bookingId", description = "booking Id")
            @Schema(description = "Reference", example = "bk_8732njsf87yh", requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable(name = "bookingId") String bookingId);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "GET", summary = "Called after payment is canceled")
    @GetMapping("/{bookingId}/payment/cancel")
    RedirectView handleBookingPaymentCancel(
            @Parameter(name = "bookingId", description = "booking Id")
            @Schema(description = "Reference", example = "bk_8732njsf87yh", requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable(name = "bookingId") String bookingId);
}