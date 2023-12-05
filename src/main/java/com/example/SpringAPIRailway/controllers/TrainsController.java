package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.DTO.RouteCompositionDTO;
import com.example.SpringAPIRailway.DTO.TrainDTO;
import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.RouteComposition;
import com.example.SpringAPIRailway.models.Train;
import com.example.SpringAPIRailway.models.TrainType;
import com.example.SpringAPIRailway.repositories.TrainRepository;
import com.example.SpringAPIRailway.repositories.TrainTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TrainsController {
    private TrainRepository trainRepository;
    private TrainTypeRepository trainTypeRepository;

    @Autowired
    public TrainsController(TrainRepository trainRepository, TrainTypeRepository trainTypeRepository) {
        this.trainRepository = trainRepository;
        this.trainTypeRepository = trainTypeRepository;
    }

    @GetMapping("/trains")
    public List<TrainDTO> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        return trains.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TrainDTO convertToDTO(Train train) {
        TrainDTO dto = new TrainDTO();
        dto.setId(train.getId());
        dto.setNumberTrain(train.getNumberTrain());
        dto.setNumberWagons(train.getNumberWagons());
        dto.setTrainTypeId(train.getTrainType().getId());
        return dto;
    }

    @GetMapping("/trains/{trainId}")
    public Train getTrainById(@PathVariable int trainId) {
        return trainRepository.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainId));
    }

    @PostMapping("/trains")
    public Train createTrain(@Valid @RequestBody Train train)
    {
        TrainType trainType = trainTypeRepository.getById(train.getTrainType().getId());
        train.setTrainType(trainType);
        return trainRepository.save(train);
    }

    @PutMapping("/trains/{trainId}")
    public Train updateTrain(@PathVariable int trainId,
                             @Valid @RequestBody Train trainRequest) {
        return trainRepository.findById(trainId)
                .map(train -> {
                    train.setNumberWagons(trainRequest.getNumberWagons());
                    train.setNumberTrain(trainRequest.getNumberTrain());
                    TrainType trainType = trainTypeRepository.getById(train.getTrainType().getId());
                    train.setTrainType(trainType);
                    return trainRepository.save(train);
                }).orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainId));
    }

    @DeleteMapping("/trains/{trainId}")
    public ResponseEntity<?> deleteTrain(@PathVariable int trainId) {
        return trainRepository.findById(trainId)
                .map(train -> {
                    trainRepository.delete(train);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainId));
    }
}