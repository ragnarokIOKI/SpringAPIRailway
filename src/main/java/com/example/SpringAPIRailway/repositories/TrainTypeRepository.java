package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.TrainType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainTypeRepository extends JpaRepository<TrainType, Integer> {
}
