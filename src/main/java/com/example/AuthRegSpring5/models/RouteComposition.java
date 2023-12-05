package com.example.AuthRegSpring5.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "route_composition")
public class RouteComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route_composition")
    private int id;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

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