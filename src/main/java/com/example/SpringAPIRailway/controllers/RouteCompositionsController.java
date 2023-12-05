package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.DTO.RouteCompositionDTO;
import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.Route;
import com.example.SpringAPIRailway.models.RouteComposition;
import com.example.SpringAPIRailway.models.Train;
import com.example.SpringAPIRailway.repositories.RouteCompositionRepository;
import com.example.SpringAPIRailway.repositories.RouteRepository;
import com.example.SpringAPIRailway.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@RestController
public class RouteCompositionsController {
    private RouteCompositionRepository routeCompositionRepository;
    private RouteRepository routeRepository;
    private TrainRepository trainRepository;

    @Autowired
    public RouteCompositionsController(RouteCompositionRepository routeCompositionRepository, RouteRepository routeRepository, TrainRepository trainRepository) {
        this.routeCompositionRepository = routeCompositionRepository;
        this.routeRepository = routeRepository;
        this.trainRepository = trainRepository;
    }

    @GetMapping("/routeCompositions")
    public List<RouteCompositionDTO> getAllComps() {
        List<RouteComposition> trains = routeCompositionRepository.findAll();
        return trains.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RouteCompositionDTO convertToDTO(RouteComposition routeComposition) {
        RouteCompositionDTO dto = new RouteCompositionDTO();
        dto.setId(routeComposition.getId());
        dto.setTrainId(routeComposition.getTrain().getId());
        dto.setRouteId(routeComposition.getRoute().getId());
        return dto;
    }

    @GetMapping("/routeCompositions/{routeCompositionId}")
    public RouteComposition getRouteCompositionById(@PathVariable int routeCompositionId) {
        return routeCompositionRepository.findById(routeCompositionId)
                .orElseThrow(() -> new ResourceNotFoundException("RouteComposition not found with id " + routeCompositionId));
    }

    @PostMapping("/routeCompositions")
    public RouteComposition createRouteComposition(@Valid @RequestBody RouteComposition routeComposition) {
        Route route = routeRepository.getById(routeComposition.getRoute().getId());
        routeComposition.setRoute(route);
        Train train = trainRepository.getById(routeComposition.getTrain().getId());
        routeComposition.setTrain(train);
        return routeCompositionRepository.save(routeComposition);
    }

    @PutMapping("/routeCompositions/{routeCompositionId}")
    public RouteComposition updateRouteComposition(@PathVariable int routeCompositionId,
                                                   @Valid @RequestBody RouteComposition routeCompositionRequest) {
        return routeCompositionRepository.findById(routeCompositionId)
                .map(routeComposition -> {
                    Route route = routeRepository.getById(routeComposition.getRoute().getId());
                    routeComposition.setRoute(route);
                    Train train = trainRepository.getById(routeComposition.getTrain().getId());
                    routeComposition.setTrain(train);
                    return routeCompositionRepository.save(routeComposition);
                }).orElseThrow(() -> new ResourceNotFoundException("RouteComposition not found with id " + routeCompositionId));
    }

    @DeleteMapping("/routeCompositions/{routeCompositionId}")
    public ResponseEntity<?> deleteRouteComposition(@PathVariable int routeCompositionId) {
        try {
            routeCompositionRepository.deleteById(routeCompositionId);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("RouteComposition not found with id " + routeCompositionId);
        } catch (ObjectOptimisticLockingFailureException ex) { throw new RuntimeException(ex); }
    }
}