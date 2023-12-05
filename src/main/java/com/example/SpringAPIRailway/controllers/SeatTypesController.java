package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.SeatType;
import com.example.SpringAPIRailway.repositories.SeatTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SeatTypesController {

    @Autowired
    private SeatTypesRepository seatTypeRepository;

    @GetMapping("/seatTypes")
    public List<SeatType> getAllSeatTypes() {
        return seatTypeRepository.findAll();
    }

    @GetMapping("/seatTypes/{seatTypeId}")
    public SeatType getSeatTypeById(@PathVariable int seatTypeId) {
        return seatTypeRepository.findById(seatTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id " + seatTypeId));
    }

    @PostMapping("/seatTypes")
    public SeatType createSeatType(@Valid @RequestBody SeatType seatType) {
        return seatTypeRepository.save(seatType);
    }

    @PutMapping("/seatTypes/{seatTypeId}")
    public SeatType updateSeatType(@PathVariable int seatTypeId,
                                   @Valid @RequestBody SeatType seatTypeRequest) {
        return seatTypeRepository.findById(seatTypeId)
                .map(seatType -> {
                    seatType.setNameSeatType(seatTypeRequest.getNameSeatType());
                    return seatTypeRepository.save(seatType);
                }).orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id " + seatTypeId));
    }

    @DeleteMapping("/seatTypes/{seatTypeId}")
    public ResponseEntity<?> deleteSeatType(@PathVariable int seatTypeId) {
        return seatTypeRepository.findById(seatTypeId)
                .map(seatType -> {
                    seatTypeRepository.delete(seatType);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("SeatType not found with id " + seatTypeId));
    }
}