package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.models.Route;
import com.example.AuthRegSpring5.models.RouteStatus;
import com.example.AuthRegSpring5.repositories.RouteRepository;
import com.example.AuthRegSpring5.repositories.RouteStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteRepository routeRepository;
    private final RouteStatusRepository routeStatusRepository;

    @Autowired
    public RouteController(RouteRepository routeRepository, RouteStatusRepository routeStatusRepository) {
        this.routeRepository = routeRepository;
        this.routeStatusRepository = routeStatusRepository;
    }

    @PostMapping("/processRoute")
    public String processRoute(@ModelAttribute Route route) {
        String url = apiBaseUrl + "/routes";
        RouteStatus selectedRouteStatus = new RouteStatus();
        selectedRouteStatus.setId(route.getRouteStatus().getId());
        selectedRouteStatus.setNameRouteStatus(routeStatusRepository.getById(route.getRouteStatus().getId()).getNameRouteStatus());
        route.setRouteStatus(selectedRouteStatus);
        new RestTemplate().postForObject(url, route, Route.class);
        return "redirect:/routes";
    }

    @GetMapping("/deleteroute/{id}")
    public String deleteRoute(@PathVariable("id") int id, Model model) {
        String url = apiBaseUrl + "/routes/" + id;
        new RestTemplate().delete(url);
        model.addAttribute("routes", routeRepository.findAll());
        return "redirect:/routes";
    }

    @PostMapping("/updateRoute")
    public String updateRoute(@ModelAttribute("route") @Valid Route route,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "routes";
        }

        String url = apiBaseUrl + "/routes/" + route.getId();
        RouteStatus selectedRouteStatus = new RouteStatus();
        selectedRouteStatus.setId(route.getRouteStatus().getId());
        selectedRouteStatus.setNameRouteStatus(routeStatusRepository.getById(route.getRouteStatus().getId()).getNameRouteStatus());
        route.setRouteStatus(selectedRouteStatus);
        new RestTemplate().put(url, route);
        return "redirect:/routes";
    }
}

