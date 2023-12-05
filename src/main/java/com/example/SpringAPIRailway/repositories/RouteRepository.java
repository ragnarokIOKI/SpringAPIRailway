package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
