package com.example.SpringAPIRailway.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "seat_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SeatType {

    @Id
    @GeneratedValue(generator = "seat_type_generator")
    @Column(name = "id_seat_type")
    @SequenceGenerator(
            name = "seat_type_generator",
            sequenceName = "seat_type_sequence",
            initialValue = 1000
    )
    private int id;

    @NotBlank(message = "Field is required")
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