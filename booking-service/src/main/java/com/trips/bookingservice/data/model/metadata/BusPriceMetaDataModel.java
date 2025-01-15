package com.trips.bookingservice.data.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusPriceMetaDataModel {
    private BigDecimal price;
    private String currency;
}
