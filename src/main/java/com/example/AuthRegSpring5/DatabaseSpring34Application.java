package com.example.AuthRegSpring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseSpring34Application {
	public static String apiBaseUrl = "http://localhost:8100";

	public static void main(String[] args) {
		SpringApplication.run(DatabaseSpring34Application.class, args);
	}

}
