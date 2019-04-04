package com.zerog.aircraft;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zerog.aircraft.model.Aircraft;
import com.zerog.aircraft.service.AircraftService;

@Component
public class AircraftRepositoryCommandLineRunner implements CommandLineRunner {

	@Autowired
	private AircraftService aircraftService;

	@Override
	public void run(String... args) throws Exception {
		Aircraft aircraft = new Aircraft("Amor", "400", "Defesa", 20, new BigDecimal(90.2), new Date());
		aircraftService.create(aircraft);

		aircraft = new Aircraft("Love", "20", "Ataque", 30, new BigDecimal(90.4), new Date());
		aircraftService.create(aircraft);

		aircraft = new Aircraft("Dealwithit", "40", "Meio Campo", 40, new BigDecimal(90.5), new Date());
		aircraftService.create(aircraft);
		
		aircraft = new Aircraft("Horse", "40", "Meio Campo", 40, new BigDecimal(90.5), new Date());
		aircraftService.create(aircraft);
	}

}