package com.example.AuthRegSpring5.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "seat_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seat_type")
    private int id;

    @Column(name = "name_seat_type", nullable = false, unique = true, length = 10)
    private String nameSeatType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSeatType() {
        return nameSeatType;
    }

    public void setNameSeatType(String nameSeatType) {
        this.nameSeatType = nameSeatType;
    }
}