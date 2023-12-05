package com.example.AuthRegSpring5.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "train_type")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TrainType {

    public TrainType() {
    }

    public TrainType(int id, String nameType) {
        this.id = id;
        this.nameType = nameType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_train_type")
    private int id;

    @Column(name = "name_type", nullable = false, unique = true)
    @NotBlank(message = "Field is required")
    private String nameType;

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