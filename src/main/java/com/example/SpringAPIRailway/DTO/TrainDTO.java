package com.example.SpringAPIRailway.DTO;

import java.util.List;

public class TrainDTO {
    private int id;
    private int numberWagons;
    private String numberTrain;
    private int trainTypeId;

    public TrainDTO() {
    }

    public TrainDTO(int id, int numberWagons, String numberTrain, int trainTypeId) {
        this.id = id;
        this.numberWagons = numberWagons;
        this.numberTrain = numberTrain;
        this.trainTypeId = trainTypeId;
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

    public int getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(int trainTypeId) {
        this.trainTypeId = trainTypeId;
    }
}
