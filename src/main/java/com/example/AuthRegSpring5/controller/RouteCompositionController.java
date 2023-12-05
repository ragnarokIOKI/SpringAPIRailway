package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.models.Route;
import com.example.AuthRegSpring5.models.RouteComposition;
import com.example.AuthRegSpring5.models.Train;
import com.example.AuthRegSpring5.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
@RequestMapping("/routecompositions")
public class RouteCompositionController {
    private final RouteCompositionRepository routeCompositionRepository;
    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public RouteCompositionController(RouteCompositionRepository routeCompositionRepository, TrainRepository trainRepository, RouteRepository routeRepository) {
        this.routeCompositionRepository = routeCompositionRepository;
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
    }

    @PostMapping("/processRouteComposition")
    public String processRouteComposition(@ModelAttribute RouteComposition routeComposition) {
        String url = apiBaseUrl + "/routeCompositions";

        Train selectedTrain = trainRepository.getById(routeComposition.getTrain().getId());
        Route selectedRoute = routeRepository.getById(routeComposition.getRoute().getId());

        routeComposition.setTrain(selectedTrain);
        routeComposition.setRoute(selectedRoute);

        new RestTemplate().postForObject(url, routeComposition, RouteComposition.class);

        return "redirect:/routecompositions";
    }

    @PostMapping("/updateRoutecomposition")
    public String updateRoutecomposition(@ModelAttribute RouteComposition routecomposition) {
        routeCompositionRepository.save(routecomposition);
        return "redirect:/routecompositions";
    }

    @GetMapping("/deleteRoutecomposition/{id}")
    public String deleteRoutecomposition(@PathVariable int id) {
        routeCompositionRepository.deleteById(id);
        return "redirect:/routecompositions";
    }
}

