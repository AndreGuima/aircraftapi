package com.adpguima.aircraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AircraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(AircraftApplication.class, args);
	}

}
