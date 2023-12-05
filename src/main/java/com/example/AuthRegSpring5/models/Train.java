package com.example.AuthRegSpring5.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "train")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_train")
    private int id;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<RouteComposition> routeCompositions;

    @Column(name = "number_wagons", nullable = false)
    private int numberWagons;

    @Column(name = "number_train", nullable = false, unique = true, length = 4)
    @NotBlank(message = "Field is required")
    private String numberTrain;

    @ManyToOne
    @JoinColumn(name = "train_type_id", nullable = false)
    private TrainType trainType;

    public Train() {
    }

    public Train(int id, String numberTrain) {
        this.id = id;
        this.numberTrain = numberTrain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
