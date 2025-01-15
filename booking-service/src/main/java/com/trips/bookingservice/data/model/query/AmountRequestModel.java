package com.trips.bookingservice.data.model.query;

import com.trips.bookingservice.enums.QueryRelationalOperator;
import com.trips.bookingservice.enums.SortingType;
import lombok.Data;

@Data
public class AmountRequestModel {
    private String amount;
    private QueryRelationalOperator queryRelationalOperator;
    private SortingType sortingType;
}
