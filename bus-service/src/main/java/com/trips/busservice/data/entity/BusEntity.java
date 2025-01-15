package com.trips.busservice.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trips.busservice.constants.BusType;
import com.trips.busservice.utils.generators.BusIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bus")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusEntity {
    @Id
    @BusIdGenerator
    @JsonProperty("busId")
    private String busId;

    @JsonProperty("busName")
    private String busName;

    @Enumerated(EnumType.STRING)
    @JsonProperty("busType")
    private BusType busType;

    @JsonProperty("operator")
    @ManyToOne
    @JoinColumn(name = "operator_id")
    private OperatorEntity operator;

    @JsonProperty("routes")
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<RouteEntity> routes = new ArrayList<>();

    @JsonProperty("capacity")
    private Integer capacity;
}
