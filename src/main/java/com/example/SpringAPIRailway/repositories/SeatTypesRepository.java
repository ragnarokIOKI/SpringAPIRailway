package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypesRepository extends JpaRepository<SeatType, Integer> {
}
