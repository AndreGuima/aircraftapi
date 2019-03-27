package com.zerog.aircraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerog.aircraft.model.Aircraft;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

}