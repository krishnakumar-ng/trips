package com.trips.bookingservice.data.model.query;

import com.trips.bookingservice.enums.QueryRelationalOperator;
import com.trips.bookingservice.enums.SortingType;
import lombok.Data;

import java.util.Date;

@Data
public class BookingDateRequestModel {
    private Date bookingDate;
    private QueryRelationalOperator queryRelationalOperator;
    private SortingType sortingType;
}
