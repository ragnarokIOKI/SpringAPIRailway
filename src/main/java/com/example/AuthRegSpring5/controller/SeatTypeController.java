package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.DatabaseSpring34Application;
import com.example.AuthRegSpring5.models.SeatType;
import com.example.AuthRegSpring5.repositories.SeatTypesRepository;
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
@RequestMapping("/seattypes")
public class SeatTypeController {
    private final SeatTypesRepository seatTypeRepository;

    @Autowired
    public SeatTypeController(SeatTypesRepository seatTypeRepository) {
        this.seatTypeRepository = seatTypeRepository;
    }

    @PostMapping("/processSeatType")
    public String processSeatType(@ModelAttribute SeatType seatType) {
        String url = apiBaseUrl + "/seatTypes";
        new RestTemplate().postForObject(url, seatType, SeatType.class);
        return "redirect:/seattypes";
    }

    @GetMapping("/deleteseatType/{id}")
    public String deleteSeatType(@PathVariable("id") int id, Model model) {
        String url = apiBaseUrl + "/seatTypes/" + id;
        new RestTemplate().delete(url);
        model.addAttribute("seatTypes", seatTypeRepository.findAll());
        return "redirect:/seattypes";
    }

    @PostMapping("/updateSeatType")
    public String updateSeatType(@ModelAttribute("seatType") @Valid SeatType seatType,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "seattypes";
        }

        String url = apiBaseUrl + "/seatTypes/" + seatType.getId();
        new RestTemplate().put(url, seatType);
        return "redirect:/seattypes";
    }
}

