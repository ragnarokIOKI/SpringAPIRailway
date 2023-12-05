package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.Route;
import com.example.SpringAPIRailway.models.RouteStatus;
import com.example.SpringAPIRailway.repositories.RouteRepository;
import com.example.SpringAPIRailway.repositories.RouteStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RouteController {
    private RouteRepository routeRepository;
    private RouteStatusRepository routeStatusRepository;

    @Autowired
    public RouteController(RouteRepository routeRepository, RouteStatusRepository routeStatusRepository) {
        this.routeRepository = routeRepository;
        this.routeStatusRepository = routeStatusRepository;
    }

    @GetMapping("/routes")
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @GetMapping("/routes/{routeId}")
    public Route getRouteById(@PathVariable int routeId) {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + routeId));
    }

    @PostMapping("/routes")
    public Route createRoute(@Valid @RequestBody Route route) {
        RouteStatus routeStatus = routeStatusRepository.getById(route.getRouteStatus().getId());
        route.setRouteStatus(routeStatus);
        return routeRepository.save(route);
    }

    @PutMapping("/routes/{routeId}")
    public Route updateRoute(@PathVariable int routeId,
                             @Valid @RequestBody Route routeRequest) {
        return routeRepository.findById(routeId)
                .map(route -> {
                    route.setNumberRoute(routeRequest.getNumberRoute());
                    route.setDepartureTimeRoute(routeRequest.getDepartureTimeRoute());
                    route.setArrivalTimeRoute(routeRequest.getArrivalTimeRoute());
                    route.setDeparturePoint(routeRequest.getDeparturePoint());
                    route.setArrivalPoint(routeRequest.getArrivalPoint());
                    RouteStatus routeStatus = routeStatusRepository.getById(route.getRouteStatus().getId());
                    route.setRouteStatus(routeStatus);
                    return routeRepository.save(route);
                }).orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + routeId));
    }

    @DeleteMapping("/routes/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable int routeId) {
        return routeRepository.findById(routeId)
                .map(route -> {
                    routeRepository.delete(route);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + routeId));
    }
}