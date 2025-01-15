package com.trips.busservice.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trips.busservice.utils.generators.RouteIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteEntity {

    @Id
    @RouteIdGenerator
    @JsonProperty("routeId")
    private String routeId;

    @JsonProperty("sourceCity")
    private String sourceCity;

    @JsonProperty("destinationCity")
    private String destinationCity;

    @JsonProperty("stops")
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<StopEntity> stops = new ArrayList<>();

    @JsonProperty("schedules")
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<ScheduleEntity> schedules = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusEntity bus;
}
