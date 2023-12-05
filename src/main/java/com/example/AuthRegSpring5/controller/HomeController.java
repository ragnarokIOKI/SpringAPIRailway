package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.DTO.RouteCompositionDTO;
import com.example.AuthRegSpring5.DTO.TrainDTO;
import com.example.AuthRegSpring5.models.*;
import com.example.AuthRegSpring5.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
public class HomeController {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    private String RegView()
    {
        return "registration";
    }

    @PostMapping("/registration")
    private String Reg(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String url = apiBaseUrl + "/users";
        new RestTemplate().postForObject(url, user, User.class);
        return "redirect:/login";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/roles")
    public String getAllRoles(Model model) {
        Role[] roles = new RestTemplate().getForObject(apiBaseUrl + "/roles", Role[].class);
        model.addAttribute("roles", roles);
        return "roles";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/users")
    public String showUsers(Model model) {
        User[] users = new RestTemplate().getForObject(apiBaseUrl + "/users", User[].class);
        Role[] roles = new RestTemplate().getForObject(apiBaseUrl + "/roles", Role[].class);
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        return "user";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/traintypes")
    public String getAllTraintypes(Model model) {
        TrainType[] traintypes = new RestTemplate().getForObject(apiBaseUrl + "/traintypes", TrainType[].class);
        model.addAttribute("trainTypes", traintypes);
        return "traintypes";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/routestatuses")
    public String getAllRoutestatuss(Model model) {
        RouteStatus[] routestatuss = new RestTemplate().getForObject(apiBaseUrl + "/routeStatuses", RouteStatus[].class);
        model.addAttribute("routeStatuses", routestatuss);
        return "routestatuses";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/seattypes")
    public String getAllSeattypes(Model model) {
        SeatType[] seattypes = new RestTemplate().getForObject(apiBaseUrl + "/seatTypes", SeatType[].class);
        model.addAttribute("seatTypes", seattypes);
        return "seattypes";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/trains")
    public String getAllTrains(Model model) {
        TrainDTO[] trainDTOs = new RestTemplate().getForObject(apiBaseUrl + "/trains", TrainDTO[].class);
        TrainType[] trainTypes = new RestTemplate().getForObject(apiBaseUrl + "/traintypes", TrainType[].class);

        List<Train> trainViewModels = new ArrayList<>();
        for (TrainDTO trainDTO : trainDTOs) {
            Train viewModel = new Train();
            viewModel.setId(trainDTO.getId());
            viewModel.setNumberTrain(trainDTO.getNumberTrain());
            viewModel.setNumberWagons(trainDTO.getNumberWagons());

            for (TrainType trainType : trainTypes) {
                if (trainType.getId() == trainDTO.getTrainTypeId()) {
                    viewModel.setTrainType(new TrainType(trainType.getId(), trainType.getNameType()));
                    break;
                }
            }

            trainViewModels.add(viewModel);
        }

        model.addAttribute("trains", trainViewModels);
        model.addAttribute("trainTypes", trainTypes);
        return "trains";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/routes")
    public String getAllRoutes(Model model) {
        Route[] routes = new RestTemplate().getForObject(apiBaseUrl + "/routes", Route[].class);
        model.addAttribute("routes", routes);
        RouteStatus[] routestatuss = new RestTemplate().getForObject(apiBaseUrl + "/routeStatuses", RouteStatus[].class);
        model.addAttribute("routeStatuses", routestatuss);
        return "routes";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/routecompositions")
    public String showRoutecompositions(Model model) {
        RouteCompositionDTO[] routeCompositionDTOs = new RestTemplate().getForObject(apiBaseUrl + "/routeCompositions", RouteCompositionDTO[].class);
        Train[] trains = new RestTemplate().getForObject(apiBaseUrl + "/trains", Train[].class);
        Route[] routes = new RestTemplate().getForObject(apiBaseUrl + "/routes", Route[].class);

        List<RouteComposition> viewModels = new ArrayList<>();
        for (RouteCompositionDTO rouCompDTO : routeCompositionDTOs) {
            RouteComposition viewModel = new RouteComposition();
            viewModel.setId(rouCompDTO.getId());

            for (Train train : trains) {
                if (train.getId() == rouCompDTO.getTrainId()) {
                    viewModel.setTrain(new Train(train.getId(), train.getNumberTrain()));
                    break;
                }
            }

            for (Route route : routes) {
                if (route.getId() == rouCompDTO.getRouteId()) {
                    viewModel.setRoute(new Route(route.getId(), route.getNumberRoute()));
                    break;
                }
            }

            viewModels.add(viewModel);
        }

        model.addAttribute("routeCompositions", viewModels);
        model.addAttribute("routes", routes);
        model.addAttribute("trains", trains);

        return "routecompositions";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/tickets")
    public String showTickets(Model model) {
        Ticket[] tickets = new RestTemplate().getForObject(apiBaseUrl + "/tickets", Ticket[].class);
        model.addAttribute("tickets", tickets);
        Route[] routes = new RestTemplate().getForObject(apiBaseUrl + "/routes", Route[].class);
        model.addAttribute("routes", routes);
        User[] users = new RestTemplate().getForObject(apiBaseUrl + "/users", User[].class);
        model.addAttribute("users", users);
        SeatType[] seattypes = new RestTemplate().getForObject(apiBaseUrl + "/seatTypes", SeatType[].class);
        model.addAttribute("seatTypes", seattypes);
        return "tickets";
    }
}

