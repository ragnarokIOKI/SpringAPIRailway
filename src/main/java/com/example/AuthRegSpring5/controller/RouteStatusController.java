package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.DatabaseSpring34Application;
import com.example.AuthRegSpring5.models.RouteStatus;
import com.example.AuthRegSpring5.repositories.RouteStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
@RequestMapping("/routestatuses")
public class RouteStatusController {
    private final RouteStatusRepository routeStatusRepository;

    @Autowired
    public RouteStatusController(RouteStatusRepository routeStatusRepository) {
        this.routeStatusRepository = routeStatusRepository;
    }

    @PostMapping("/processRouteStatus")
    public String processRouteStatus(@ModelAttribute RouteStatus routeStatus) {
        String url = apiBaseUrl + "/routeStatuses";
        new RestTemplate().postForObject(url, routeStatus, RouteStatus.class);
        return "redirect:/routestatuses";
    }

    @GetMapping("/deleterouteStatus/{id}")
    public String deleteRouteStatus(@PathVariable("id") int id, Model model) {
        String url = apiBaseUrl + "/routeStatuses/" + id;
        new RestTemplate().delete(url);
        model.addAttribute("routeStatuses", routeStatusRepository.findAll());
        return "redirect:/routestatuses";
    }

    @PostMapping("/updateRouteStatus")
    public String updateRouteStatus(@ModelAttribute("routeStatus") @Valid RouteStatus routeStatus,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "routeStatuses";
        }

        String url = apiBaseUrl + "/routeStatuses/" + routeStatus.getId();
        new RestTemplate().put(url, routeStatus);
        return "redirect:/routestatuses";
    }
}

