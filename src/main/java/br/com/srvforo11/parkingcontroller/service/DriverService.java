package br.com.srvforo11.parkingcontroller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.srvforo11.parkingcontroller.domain.entity.Driver;
import br.com.srvforo11.parkingcontroller.repository.DriverRepository;

@Service
public class DriverService {

	private DriverRepository driverRepository;
	
	public DriverService(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}

	
	public List<Driver> list(){
		return driverRepository.findAll(); 
	}
}
