package com.example.AuthRegSpring5.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private int id;

    @Column(name = "name_role", nullable = false, unique = true)
    @NotBlank(message = "Field is required")
    private String nameRole;

    public Role(int id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }

    public Role() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
}
