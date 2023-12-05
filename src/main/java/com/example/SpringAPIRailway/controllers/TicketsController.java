package com.example.SpringAPIRailway.controllers;

import com.example.SpringAPIRailway.exceptions.ResourceNotFoundException;
import com.example.SpringAPIRailway.models.*;
import com.example.SpringAPIRailway.repositories.RouteRepository;
import com.example.SpringAPIRailway.repositories.SeatTypesRepository;
import com.example.SpringAPIRailway.repositories.TicketsRepository;
import com.example.SpringAPIRailway.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TicketsController {

    private TicketsRepository ticketRepository;
    private RouteRepository routeRepository;
    private UserRepository userRepository;
    private SeatTypesRepository seatTypeRepository;

    @Autowired
    public TicketsController(TicketsRepository ticketRepository, RouteRepository routeRepository, UserRepository userRepository, SeatTypesRepository seatTypeRepository) {
        this.ticketRepository = ticketRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.seatTypeRepository = seatTypeRepository;
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/tickets/{numberTicket}")
    public Ticket getTicketByNumber(@PathVariable String numberTicket) {
        return ticketRepository.findById(Integer.valueOf(numberTicket))
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with number " + numberTicket));
    }

    @PostMapping("/tickets")
    public Ticket createTicket(@Valid @RequestBody Ticket ticket) {
        Route route = routeRepository.getById(ticket.getRoute().getId());
        ticket.setRoute(route);
        User user = userRepository.getById(ticket.getUser().getId());
        ticket.setUser(user);
        SeatType seattype = seatTypeRepository.getById(ticket.getSeatType().getId());
        ticket.setSeatType(seattype);
        return ticketRepository.save(ticket);
    }

    @PutMapping("/tickets/{numberTicket}")
    public Ticket updateTicket(@PathVariable String numberTicket,
                               @Valid @RequestBody Ticket ticketRequest) {
        return ticketRepository.findById(Integer.valueOf(numberTicket))
                .map(ticket -> {
                    Route route = routeRepository.getById(ticket.getRoute().getId());
                    ticket.setRoute(route);
                    User user = userRepository.getById(ticket.getUser().getId());
                    ticket.setUser(user);
                    SeatType seattype = seatTypeRepository.getById(ticket.getSeatType().getId());
                    ticket.setSeatType(seattype);
                    ticket.setNumberSeat(ticketRequest.getNumberSeat());
                    return ticketRepository.save(ticket);
                }).orElseThrow(() -> new ResourceNotFoundException("Ticket not found with number " + numberTicket));
    }

    @DeleteMapping("/tickets/{numberTicket}")
    public ResponseEntity<?> deleteTicket(@PathVariable String numberTicket) {
        return ticketRepository.findById(Integer.valueOf(numberTicket))
                .map(ticket -> {
                    ticketRepository.delete(ticket);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Ticket not found with number " + numberTicket));
    }
}