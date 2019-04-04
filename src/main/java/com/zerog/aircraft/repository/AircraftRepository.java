package com.zerog.aircraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.zerog.aircraft.model.Aircraft;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

}