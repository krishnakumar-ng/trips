package com.trips.busservice.api.resource;

import com.trips.busservice.constants.ApiConstants;
import com.trips.busservice.data.dto.BusDetailsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.trips.busservice.constants.ApiConstants.*;

@Tag(name = "bus-controller-resource")

@RequestMapping(value = "/" + API + "/" + VERSION1 + BUS_SERVICE_ENDPOINT)
public interface BusResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Add Bus request")
    @PostMapping
    ResponseEntity<BusDetailsDto> addBus(@RequestBody @Valid BusDetailsDto busDetailsDto);


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "PATCH", summary = "Update Bus Availability")
    @PatchMapping("/{busId}/availability")
    ResponseEntity<BusDetailsDto> updateBusSeatsAvailability(
            @Parameter(name = "busId", description = "bus id")
            @Schema(description = "Reference", example = "KPN Travels - TN 05 A 1234")
            @PathVariable(name = "busId") String busId,
            @RequestParam(name = "isAvailable") boolean isAvailable,
            @RequestParam(name = "date") String date
    );


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "PATCH", summary = "Update Bus Seats")
    @PatchMapping("/{busId}/seats")
    ResponseEntity<BusDetailsDto> updateBusSeatsAvailability(
            @Parameter(name = "busId", description = "bus id")
            @Schema(description = "Reference", example = "KPN Travels - TN 05 A 1234")
            @PathVariable(name = "busId") String busId,
            @RequestParam(name = "seats") int seats,
            @RequestParam(name = "date") String date
    );


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "PATCH", summary = "Update Bus Details")
    @PutMapping("/{busId}")
    ResponseEntity<BusDetailsDto> updateBusDetails(
            @Parameter(name = "busId", description = "bus id")
            @Schema(description = "Reference", example = "KPN Travels - TN 05 A 1234")
            @PathVariable(name = "busId") String busId,
            @RequestBody @Valid BusDetailsDto busDetailsDto
    );


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "GET", summary = "Get All buses")
    @GetMapping("/all")
    ResponseEntity<List<BusDetailsDto>> getAllBuses();


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "GET", summary = "Get bus by id")
    @GetMapping("/{busId}")
    ResponseEntity<BusDetailsDto> getBusById(
            @Parameter(name = "busId", description = "bus id")
            @Schema(description = "Reference", example = "KPN Travels - TN 05 A 1234")
            @PathVariable String busId
    );


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "404", description = ApiConstants.MESSAGE_NOT_FOUND)
            }
    )
    @Operation(method = "GET", summary = "Get using from and to locations and the date")
    @GetMapping()
    ResponseEntity<List<BusDetailsDto>> getBusByLocationAndDate(
            @RequestParam(name = "from") String from,
            @RequestParam(name = "to") String to,
            @RequestParam(name = "date") String date
    );
}
