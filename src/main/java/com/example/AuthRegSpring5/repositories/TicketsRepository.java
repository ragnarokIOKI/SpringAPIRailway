package com.example.AuthRegSpring5.repositories;

import com.example.AuthRegSpring5.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Ticket, Integer> {
}
