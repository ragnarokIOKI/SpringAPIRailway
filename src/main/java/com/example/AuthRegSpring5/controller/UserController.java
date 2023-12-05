package com.example.AuthRegSpring5.controller;

import com.example.AuthRegSpring5.models.User;
import com.example.AuthRegSpring5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.example.AuthRegSpring5.DatabaseSpring34Application.apiBaseUrl;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam("lastName") String lastName, Model model) {
        String url = apiBaseUrl + "/users/search?lastName=" + lastName;
        User[] foundUsers = new RestTemplate().getForObject(url, User[].class);
        model.addAttribute("users", foundUsers);
        return "user";
    }

    @PostMapping("/processUser")
    public String processUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String url = apiBaseUrl + "/users";
        new RestTemplate().postForObject(url, user, User.class);
        return "redirect:/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String url = apiBaseUrl + "/users/" + id;
        new RestTemplate().delete(url);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String url = apiBaseUrl + "/users/" + user.getId();
        new RestTemplate().put(url, user);
        return "redirect:/users";
    }
}