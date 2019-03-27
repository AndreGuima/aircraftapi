package com.adpguima.aircraft.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adpguima.aircraft.model.Aircraft;
import com.adpguima.aircraft.service.AircraftService;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {

	@Autowired
	AircraftService aircraftService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aircraft create(@Valid Aircraft aircraft) {
		return this.aircraftService.create(aircraft);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody Aircraft aircraft) {
		this.aircraftService.update(aircraft);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Aircraft> read() {
		return this.aircraftService.readAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestParam Long id) {
		this.aircraftService.delete(id);
	}

}
