package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.RouteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteStatusRepository extends JpaRepository<RouteStatus, Integer> {
}
