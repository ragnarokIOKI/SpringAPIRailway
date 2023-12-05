package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.exceptions.ResourceNotFoundException;
import com.example.AuthRegSpring5.models.TrainType;
import com.example.AuthRegSpring5.repositories.TrainTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@RestController
@RequestMapping("/traintypes")
public class TrainTypeController {
    private final TrainTypeRepository trainTypeRepository;

    @Autowired
    public TrainTypeController(TrainTypeRepository trainTypeRepository) {
        this.trainTypeRepository = trainTypeRepository;
    }

    @PostMapping("/processTrainType")
    public String processTrainType(@ModelAttribute TrainType trainType) {
        String url = apiBaseUrl + "/trainTypes";
        new RestTemplate().postForObject(url, trainType, TrainType.class);
        return "redirect:/traintypes";
    }

    @GetMapping("/deletetrainType/{id}")
    public String deleteTrainType(@PathVariable("id") int id, Model model) {
        String url = apiBaseUrl + "/trainTypes/" + id;
        new RestTemplate().delete(url);
        model.addAttribute("trainTypes", trainTypeRepository.findAll());
        return "redirect:/traintypes";
    }

    @PostMapping("/updateTrainType")
    public String updateTrainType(@ModelAttribute("trainType") @Valid TrainType trainType,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "trainTypes";
        }

        String url = apiBaseUrl + "/trainTypes/" + trainType.getId();
        new RestTemplate().put(url, trainType);
        return "redirect:/traintypes";
    }
}