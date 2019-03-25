package com.adpguima.aircraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adpguima.aircraft.model.Aircraft;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

}