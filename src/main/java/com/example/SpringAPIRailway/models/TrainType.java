package com.example.SpringAPIRailway.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "train_type")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TrainType {

    @Id
    @GeneratedValue(generator = "train_type_generator")
    @Column(name = "id_train_type")
    @SequenceGenerator(
            name = "train_type_generator",
            sequenceName = "train_type_sequence",
            initialValue = 1000
    )
    private int id;

    @NotBlank(message = "Field is required")
    @Column(name = "name_type", nullable = false, unique = true)
    private String nameType;

    public TrainType() {
    }

    public TrainType(int id, String nameType) {
        this.id = id;
        this.nameType = nameType;
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