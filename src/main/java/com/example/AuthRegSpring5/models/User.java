package com.example.AuthRegSpring5.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private int id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Field is required")
    private String firstName;

    @Column(name = "second_name", nullable = false)
    @NotBlank(message = "Field is required")
    private String secondName;

    @Column(name = "middle_name", nullable = true, columnDefinition = "varchar(30) default '-'")
    @NotBlank(message = "Field is required")
    private String middleName;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "passport_series", nullable = false, length = 5)
    @NotBlank(message = "Field is required")
    private String passportSeries;

    @Column(name = "passport_number", nullable = false, length = 6)
    @NotBlank(message = "Field is required")
    private String passportNumber;

    @Column(name = "card_number", nullable = false, unique = true, length = 19)
    @NotBlank(message = "Field is required")
    private String cardNumber;

    @Column(name = "card_holder", nullable = false, length = 100)
    @NotBlank(message = "Field is required")
    private String cardHolder;

    @Column(name = "validity", nullable = false, length = 5)
    @NotBlank(message = "Field is required")
    private String validity;

    @Column(name = "username", nullable = false, unique = true, length = 32)
    @NotBlank(message = "Field is required")
    private String username;

    @Column(name = "password", nullable = false, length = 32)
    @NotBlank(message = "Field is required")
    private String password;

    @Column(name = "active", nullable = false, length = 32)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}