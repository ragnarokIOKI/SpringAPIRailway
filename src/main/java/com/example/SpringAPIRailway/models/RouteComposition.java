package com.example.SpringAPIRailway.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "route_composition")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class RouteComposition {

    @Id
    @GeneratedValue(generator = "route_composition_generator")
    @Column(name = "id_route_composition")
    @SequenceGenerator(
            name = "route_composition_generator",
            sequenceName = "route_composition_sequence",
            initialValue = 1000
    )
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id", nullable = false)
    @JsonBackReference
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    public RouteComposition(int id, Train train, Route route) {
        this.id = id;
        this.train = train;
        this.route = route;
    }

    public RouteComposition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}