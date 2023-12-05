package com.example.AuthRegSpring5.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "route_status")
public class RouteStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route_status")
    private int id;

    @Column(name = "name_route_status", nullable = false, unique = true)
    @NotBlank
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