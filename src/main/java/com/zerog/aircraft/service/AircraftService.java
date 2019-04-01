package com.zerog.aircraft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerog.aircraft.exception.AircraftConflictException;
import com.zerog.aircraft.exception.AircraftNotFoundException;
import com.zerog.aircraft.model.Aircraft;
import com.zerog.aircraft.repository.AircraftRepository;

@Service
public class AircraftService {

	@Autowired
	AircraftRepository aircraftRepository;

	public Aircraft create(Aircraft aircraft) {

		if (aircraft.getId() != null && this.aircraftRepository.findById(aircraft.getId()) != null) {
			throw new AircraftConflictException("Duplicated aircraft with id " + aircraft.getId());
		} else {
			return this.aircraftRepository.save(aircraft);
		}

	}

	public Aircraft update(final Long id, final Aircraft newAircraft) {

		return this.aircraftRepository.findById(id).map(aircraft -> {
			aircraft.setName(newAircraft.getName());
			aircraft.setSerialNumber(newAircraft.getSerialNumber());
			aircraft.setUn(newAircraft.getUn());
			aircraft.setCapacity(newAircraft.getCapacity());
			aircraft.setManufactureDate(newAircraft.getManufactureDate());
			aircraft.setWeight(newAircraft.getWeight());

			return aircraftRepository.save(aircraft);
		}).orElseThrow(() -> new AircraftNotFoundException("Aircraft not found " + id));
	}

	public List<Aircraft> findAll() {
		return this.aircraftRepository.findAll();
	}

	public Aircraft findById(Long id) {
		return this.aircraftRepository.findById(id)
				.orElseThrow(() -> new AircraftNotFoundException("Aircraft not found " + id));
	}

	public void delete(Long id) {
		this.aircraftRepository.deleteById(id);
	}

}