package com.trips.busservice.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stop")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty("stopId")
    private String stopId;

    @JsonProperty("stopName")
    private String stopName;

    @JsonProperty("stopOrder")
    private Integer stopOrder;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteEntity route;
}
