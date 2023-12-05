package com.example.AuthRegSpring5.repositories;

import com.example.AuthRegSpring5.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {

}