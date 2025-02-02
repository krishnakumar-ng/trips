package com.trips.paymentservice.data.model.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RevenueStatisticsResponseModel {
    @Schema(description = "current", example = "1200")
    List<Long> curr;

    @Schema(description = "prev", example = "1200")
    List<Long> prev;
}
