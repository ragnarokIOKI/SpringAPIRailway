package com.example.AuthRegSpring5.repositories;

import com.example.AuthRegSpring5.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypesRepository extends JpaRepository<SeatType, Integer> {
}
