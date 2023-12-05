package com.example.SpringAPIRailway.DTO;

public class TrainTypeDTO {
    private int id;
    private String nameType;

    public TrainTypeDTO(int id, String nameType) {
        this.id = id;
        this.nameType = nameType;
    }

    public TrainTypeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
}
