package com.example.SpringAPIRailway.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "train")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Train {

    @Id
    @GeneratedValue(generator = "train_generator")
    @Column(name = "id_train")
    @SequenceGenerator(
            name = "train_generator",
            sequenceName = "train_sequence",
            initialValue = 1000
    )
    private int id;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    //@JsonIgnore
    private List<RouteComposition> routeCompositions;

    @Column(name = "number_wagons", nullable = false)
    private int numberWagons;

    @NotBlank(message = "Field is required")
    @Column(name = "number_train", nullable = false, unique = true, length = 4)
    private String numberTrain;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "train_type_id", nullable = false)
    private TrainType trainType;

    public Train(int id, List<RouteComposition> routeCompositions, int numberWagons, String numberTrain, TrainType trainType) {
        this.id = id;
        this.routeCompositions = routeCompositions;
        this.numberWagons = numberWagons;
        this.numberTrain = numberTrain;
        this.trainType = trainType;
    }

    public Train() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RouteComposition> getRouteCompositions() {
        return routeCompositions;
    }

    public void setRouteCompositions(List<RouteComposition> routeCompositions) {
        this.routeCompositions = routeCompositions;
    }

    public int getNumberWagons() {
        return numberWagons;
    }

    public void setNumberWagons(int numberWagons) {
        this.numberWagons = numberWagons;
    }

    public String getNumberTrain() {
        return numberTrain;
    }

    public void setNumberTrain(String numberTrain) {
        this.numberTrain = numberTrain;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }
}