package com.travel_planner_be.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TravelApplication {

	public static void main(String[] args) {

		SpringApplication.run(TravelApplication.class, args);
	}




}
