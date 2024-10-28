package com.example.forextracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ForextrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForextrackerApplication.class, args);
	}

}
