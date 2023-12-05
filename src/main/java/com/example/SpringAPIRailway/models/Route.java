package com.example.SpringAPIRailway.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "route")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Route {

    @Id
    @GeneratedValue(generator = "route_generator")
    @Column(name = "id_route")
    @SequenceGenerator(
            name = "route_generator",
            sequenceName = "route_sequence",
            initialValue = 1000
    )
    private int id;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JsonIgnore
    @JoinTable(
            name = "route_composition",
            joinColumns = { @JoinColumn(name = "route_id") },
            inverseJoinColumns = { @JoinColumn(name = "train_id") }
    )
    private Set<Train> trains = new HashSet<>();

    @NotBlank(message = "Field is required")
    @Column(name = "number_route", nullable = false, unique = true, length = 4)
    private String numberRoute;

    @Column(name = "departure_time_route", nullable = false)
    private Time departureTimeRoute;

    @Column(name = "arrival_time_route", nullable = false)
    private Time arrivalTimeRoute;

    @NotBlank(message = "Field is required")
    @Column(name = "departure_point", nullable = false, length = 30)
    private String departurePoint;

    @NotBlank(message = "Field is required")
    @Column(name = "arrival_point", nullable = false, length = 30)
    private String arrivalPoint;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "route_status_id", nullable = false)
    private RouteStatus routeStatus;

    public Route(int id, Set<Train> trains, String numberRoute, Time departureTimeRoute, Time arrivalTimeRoute, String departurePoint, String arrivalPoint, RouteStatus routeStatus) {
        this.id = id;
        this.trains = trains;
        this.numberRoute = numberRoute;
        this.departureTimeRoute = departureTimeRoute;
        this.arrivalTimeRoute = arrivalTimeRoute;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.routeStatus = routeStatus;
    }

    public Route() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
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