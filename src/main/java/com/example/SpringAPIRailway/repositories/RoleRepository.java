package com.example.SpringAPIRailway.repositories;

import com.example.SpringAPIRailway.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
