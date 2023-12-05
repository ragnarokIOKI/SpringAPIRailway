package com.example.AuthRegSpring5.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "route")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_route")
    private int id;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "route_composition",
            joinColumns = { @JoinColumn(name = "route_id") },
            inverseJoinColumns = { @JoinColumn(name = "train_id") }
    )
    private Set<Train> trains = new HashSet<>();

    @Column(name = "number_route", nullable = false, unique = true, length = 4)
    @NotBlank(message = "Field is required")
    private String numberRoute;

    @Column(name = "departure_time_route", nullable = false)
    private Time departureTimeRoute;

    @Column(name = "arrival_time_route", nullable = false)
    private Time arrivalTimeRoute;

    @Column(name = "departure_point", nullable = false, length = 30)
    @NotBlank(message = "Field is required")
    private String departurePoint;

    @Column(name = "arrival_point", nullable = false, length = 30)
    @NotBlank(message = "Field is required")
    private String arrivalPoint;

    @ManyToOne
    @JoinColumn(name = "route_status_id", nullable = false)
    private RouteStatus routeStatus;

    public Route() {
    }

    public Route(int id, String numberRoute) {
        this.id = id;
        this.numberRoute = numberRoute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberRoute() {
        return numberRoute;
    }

    public void setNumberRoute(String numberRoute) {
        this.numberRoute = numberRoute;
    }

    public Time getDepartureTimeRoute() {
        return departureTimeRoute;
    }

    public void setDepartureTimeRoute(Time departureTimeRoute) {
        this.departureTimeRoute = departureTimeRoute;
    }

    public Time getArrivalTimeRoute() {
        return arrivalTimeRoute;
    }

    public void setArrivalTimeRoute(Time arrivalTimeRoute) {
        this.arrivalTimeRoute = arrivalTimeRoute;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public RouteStatus getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(RouteStatus routeStatus) {
        this.routeStatus = routeStatus;
    }
}