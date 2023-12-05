package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.DatabaseSpring34Application;
import com.example.AuthRegSpring5.models.Role;
import com.example.AuthRegSpring5.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping("/processRole")
    public String processRole(@ModelAttribute Role role) {
        String url = apiBaseUrl + "/roles";
        new RestTemplate().postForObject(url, role, Role.class);
        return "redirect:/roles";
    }

    @GetMapping("/deleterole/{id}")
    public String deleteRole(@PathVariable("id") int id, Model model) {
        String url = apiBaseUrl + "/roles/" + id;
        new RestTemplate().delete(url);
        model.addAttribute("roles", roleRepository.findAll());
        return "redirect:/roles";
    }

    @PostMapping("/updateRole")
    public String updateRole(@ModelAttribute("role") @Valid Role role,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "roles";
        }

        String url = apiBaseUrl + "/roles/" + role.getId();
        new RestTemplate().put(url, role);
        return "redirect:/roles";
    }
}