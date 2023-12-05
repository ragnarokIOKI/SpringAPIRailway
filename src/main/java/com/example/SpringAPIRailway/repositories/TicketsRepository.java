package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Ticket, Integer> {
}
