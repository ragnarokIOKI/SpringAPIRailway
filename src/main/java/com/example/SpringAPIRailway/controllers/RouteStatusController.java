package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.RouteStatus;
import com.example.SpringAPIRailway.repositories.RouteStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RouteStatusController {

    @Autowired
    private RouteStatusRepository routeStatusRepository;

    @GetMapping("/routeStatuses")
    public List<RouteStatus> getAllRouteStatuses() {
        return routeStatusRepository.findAll();
    }

    @GetMapping("/routeStatuses/{routeStatusId}")
    public RouteStatus getRouteStatusById(@PathVariable int routeStatusId) {
        return routeStatusRepository.findById(routeStatusId)
                .orElseThrow(() -> new ResourceNotFoundException("RouteStatus not found with id " + routeStatusId));
    }

    @PostMapping("/routeStatuses")
    public RouteStatus createRouteStatus(@Valid @RequestBody RouteStatus routeStatus) {
        return routeStatusRepository.save(routeStatus);
    }

    @PutMapping("/routeStatuses/{routeStatusId}")
    public RouteStatus updateRouteStatus(@PathVariable int routeStatusId,
                                         @Valid @RequestBody RouteStatus routeStatusRequest) {
        return routeStatusRepository.findById(routeStatusId)
                .map(routeStatus -> {
                    routeStatus.setNameRouteStatus(routeStatusRequest.getNameRouteStatus());
                    return routeStatusRepository.save(routeStatus);
                }).orElseThrow(() -> new ResourceNotFoundException("RouteStatus not found with id " + routeStatusId));
    }

    @DeleteMapping("/routeStatuses/{routeStatusId}")
    public ResponseEntity<?> deleteRouteStatus(@PathVariable int routeStatusId) {
        return routeStatusRepository.findById(routeStatusId)
                .map(routeStatus -> {
                    routeStatusRepository.delete(routeStatus);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("RouteStatus not found with id " + routeStatusId));
    }
}