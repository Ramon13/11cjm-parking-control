package br.com.srvforo11.parkingcontroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.srvforo11.parkingcontroller.domain.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, String>{}
