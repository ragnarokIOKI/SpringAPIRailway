package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.User;
import com.example.SpringAPIRailway.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable int userId,
                           @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(userRequest.getFirstName());
                    user.setSecondName(userRequest.getSecondName());
                    user.setMiddleName(userRequest.getMiddleName());
                    user.setBirthday(userRequest.getBirthday());
                    user.setPassportSeries(userRequest.getPassportSeries());
                    user.setPassportNumber(userRequest.getPassportNumber());
                    user.setCardNumber(userRequest.getCardNumber());
                    user.setCardHolder(userRequest.getCardHolder());
                    user.setValidity(userRequest.getValidity());
                    user.setUsername(userRequest.getUsername());
                    user.setPassword(userRequest.getPassword());
                    user.setActive(userRequest.isActive());
                    user.setRole(userRequest.getRole());
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @GetMapping("/search")
    public List<User> getUsersByLastName(@RequestParam String lastName) {
        return userRepository.findByLastName(lastName);
    }
}