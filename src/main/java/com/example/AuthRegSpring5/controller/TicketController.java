package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.models.*;
import com.example.AuthRegSpring5.repositories.SeatTypesRepository;
import com.example.AuthRegSpring5.repositories.TicketsRepository;
import com.example.AuthRegSpring5.repositories.RouteRepository;
import com.example.AuthRegSpring5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketsRepository ticketRepository;
    private final RouteRepository routeRepository;
    private final SeatTypesRepository seatTypesRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketController(TicketsRepository ticketRepository, RouteRepository routeRepository, SeatTypesRepository seatTypesRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.routeRepository = routeRepository;
        this.seatTypesRepository = seatTypesRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/processTicket")
    public String processTicket(@ModelAttribute Ticket ticket) {
        String url = apiBaseUrl + "/tickets";

        SeatType selectedSeatType = seatTypesRepository.getById(ticket.getSeatType().getId());
        ticket.setSeatType(selectedSeatType);

        User selectedUser = userRepository.getById(ticket.getUser().getId());
        ticket.setUser(selectedUser);

        Route selectedRoute = routeRepository.getById(ticket.getRoute().getId());
        ticket.setRoute(selectedRoute);

        Ticket newTicket = new Ticket();
        newTicket.setNumberTicket(ticket.getNumberTicket());
        newTicket.setNumberSeat(ticket.getNumberSeat());
        newTicket.setSeatType(ticket.getSeatType());
        newTicket.setUser(ticket.getUser());
        newTicket.setRoute(ticket.getRoute());

        new RestTemplate().postForObject(url, newTicket, Ticket.class);

        return "redirect:/tickets";
    }

    @GetMapping("/deleteticket/{id}")
    public String deleteTicket(@PathVariable("id") int id, Model model) {
        String url = apiBaseUrl + "/tickets/" + id;
        new RestTemplate().delete(url);
        model.addAttribute("tickets", ticketRepository.findAll());
        return "redirect:/tickets";
    }

    @PostMapping("/updateTicket")
    public String updateTicket(@ModelAttribute("ticket") @Valid Ticket ticket,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "tickets";
        }

        String url = apiBaseUrl + "/tickets/" + ticket.getId();
        SeatType selectedSeatType = seatTypesRepository.getById(ticket.getSeatType().getId());
        ticket.setSeatType(selectedSeatType);

        User selectedUser = userRepository.getById(ticket.getUser().getId());
        ticket.setUser(selectedUser);

        Route selectedRoute = routeRepository.getById(ticket.getRoute().getId());
        ticket.setRoute(selectedRoute);

        Ticket newTicket = new Ticket();
        newTicket.setId(ticket.getId());
        newTicket.setNumberTicket(ticket.getNumberTicket());
        newTicket.setNumberSeat(ticket.getNumberSeat());
        newTicket.setSeatType(ticket.getSeatType());
        newTicket.setUser(ticket.getUser());
        newTicket.setRoute(ticket.getRoute());

        new RestTemplate().put(url, newTicket);
        return "redirect:/tickets";
    }
}

