package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT p FROM User p WHERE p.firstName = :lastName")
    List<User> findByLastName(@Param("lastName") String lastName);
    User findByUsername(String username);
}
