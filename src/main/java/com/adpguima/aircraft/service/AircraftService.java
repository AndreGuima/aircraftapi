package com.adpguima.aircraft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adpguima.aircraft.model.Aircraft;
import com.adpguima.aircraft.repository.AircraftRepository;

@Service
public class AircraftService {

	@Autowired
	AircraftRepository aircraftRepository;

	public Aircraft create(Aircraft aircraft) {
		return this.aircraftRepository.save(aircraft);
	}

	public void update(Aircraft aircraft) {
		this.aircraftRepository.save(aircraft);
	}

	public List<Aircraft> read() {
		return this.aircraftRepository.findAll();
	}

	public void delete(Long id) {
		this.aircraftRepository.deleteById(id);
	}

}