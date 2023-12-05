package com.example.AuthRegSpring5.repositories;

import com.example.AuthRegSpring5.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
