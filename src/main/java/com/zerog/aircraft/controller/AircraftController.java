package com.zerog.aircraft.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zerog.aircraft.model.Aircraft;
import com.zerog.aircraft.service.AircraftService;

@RestController
@RequestMapping("/aircrafts")
public class AircraftController {

	@Autowired
	AircraftService aircraftService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aircraft create(@Valid @RequestBody final Aircraft aircraft) {
		return this.aircraftService.create(aircraft);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Aircraft update(@PathVariable("id") final Long id, @RequestBody final Aircraft newAircraft) {
		return this.aircraftService.update(id, newAircraft);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Aircraft> findAll() {
		return this.aircraftService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Aircraft findById(@PathVariable("id") final Long id) {
		return this.aircraftService.findById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestParam Long id) {
		this.aircraftService.delete(id);
	}

}
