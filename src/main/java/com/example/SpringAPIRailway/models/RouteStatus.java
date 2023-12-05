package com.example.SpringAPIRailway.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "route_status")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class RouteStatus {

    @Id
    @GeneratedValue(generator = "route_status_generator")
    @Column(name = "id_route_status")
    @SequenceGenerator(
            name = "route_status_generator",
            sequenceName = "route_status_sequence",
            initialValue = 1000
    )
    private int id;

    @NotBlank(message = "Field is required")
    @Column(name = "name_route_status", nullable = false, unique = true)
    private String nameRouteStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRouteStatus() {
        return nameRouteStatus;
    }

    public void setNameRouteStatus(String nameRouteStatus) {
        this.nameRouteStatus = nameRouteStatus;
    }
}