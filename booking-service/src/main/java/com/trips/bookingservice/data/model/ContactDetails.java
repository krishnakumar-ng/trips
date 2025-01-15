package com.trips.bookingservice.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDetails {
    @Schema(description = "Full Name", example = "Krishnakumar", requiredMode = Schema.RequiredMode.REQUIRED)
    private String fullName;

    @Schema(description = "email", example = "name@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String emailId;

    @Schema(description = "phone no", example = "+91 1234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneNumber;
}
