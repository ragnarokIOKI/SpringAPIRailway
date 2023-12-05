package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.TrainType;
import com.example.SpringAPIRailway.repositories.TrainTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TrainTypesController {

    @Autowired
    private TrainTypeRepository trainTypeRepository;

    @GetMapping("/traintypes")
    public List<TrainType> getAllTrainTypes() {
        return trainTypeRepository.findAll();
    }

    @GetMapping("/traintypes/{trainTypeId}")
    public TrainType getTrainTypeById(@PathVariable int trainTypeId) {
        return trainTypeRepository.findById(trainTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("TrainType not found with id " + trainTypeId));
    }

    @PostMapping("/traintypes")
    public TrainType createTrainType(@Valid @RequestBody TrainType trainType) {
        return trainTypeRepository.save(trainType);
    }

    @PutMapping("/traintypes/{trainTypeId}")
    public TrainType updateTrainType(@PathVariable int trainTypeId,
                                     @Valid @RequestBody TrainType trainTypeRequest) {
        return trainTypeRepository.findById(trainTypeId)
                .map(trainType -> {
                    trainType.setNameType(trainTypeRequest.getNameType());
                    return trainTypeRepository.save(trainType);
                }).orElseThrow(() -> new ResourceNotFoundException("TrainType not found with id " + trainTypeId));
    }

    @DeleteMapping("/traintypes/{trainTypeId}")
    public ResponseEntity<?> deleteTrainType(@PathVariable int trainTypeId) {
        return trainTypeRepository.findById(trainTypeId)
                .map(trainType -> {
                    trainTypeRepository.delete(trainType);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("TrainType not found with id " + trainTypeId));
    }
}