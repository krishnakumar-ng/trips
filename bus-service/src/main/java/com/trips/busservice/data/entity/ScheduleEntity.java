package com.trips.busservice.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trips.busservice.constants.Frequency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("scheduleId")
    private Long scheduleId;

    @JsonProperty("departureTime")
    private LocalTime departureTime;

    @JsonProperty("arrivalTime")
    private LocalTime arrivalTime;

    @JsonProperty("frequency")
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("dates")
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<ScheduleDateEntity> dates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteEntity route;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusEntity bus;
}
