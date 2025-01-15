package com.trips.bookingservice.data.model.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusMetaDataModel {
    private String id;
    private String busName;
    private String operatorId;
    private BusPriceMetaDataModel price;
}
