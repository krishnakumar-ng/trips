package com.trips.bookingservice.data.dto;

import com.trips.bookingservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDetailsDto {
    private String name;
    private Integer age;
    private Gender gender;
}
