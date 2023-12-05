package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {

}