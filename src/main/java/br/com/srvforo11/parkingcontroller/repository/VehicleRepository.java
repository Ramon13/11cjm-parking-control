package br.com.srvforo11.parkingcontroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.srvforo11.parkingcontroller.domain.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{}
