package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.models.Train;
import com.example.AuthRegSpring5.models.TrainType;
import com.example.AuthRegSpring5.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
@RequestMapping("/trains")
public class TrainController {
    private final TrainRepository trainRepository;

    @Autowired
    public TrainController(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @PostMapping("/processTrain")
    public String processTrain(@ModelAttribute Train train) {
        String url = apiBaseUrl + "/trains";
        TrainType selectedTrainType = new TrainType();
        selectedTrainType.setId(train.getTrainType().getId());
        selectedTrainType.setNameType(train.getTrainType().getNameType());
        train.setTrainType(selectedTrainType);
        new RestTemplate().postForObject(url, train, Train.class);
        return "redirect:/trains";
    }

    @GetMapping("/deletetrain/{id}")
    public String deleteTrain(@PathVariable("id") int id, Model model) {
        String url = apiBaseUrl + "/trains/" + id;
        new RestTemplate().delete(url);
        model.addAttribute("trains", trainRepository.findAll());
        return "redirect:/trains";
    }

    @PostMapping("/updateTrain")
    public String updateTrain(@ModelAttribute("train") @Valid Train train,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "trains";
        }

        String url = apiBaseUrl + "/trains/" + train.getId();
        TrainType selectedTrainType = new TrainType();
        selectedTrainType.setId(train.getTrainType().getId());
        selectedTrainType.setNameType(train.getTrainType().getNameType());
        train.setTrainType(selectedTrainType);
        new RestTemplate().put(url, train);
        return "redirect:/trains";
    }
}

