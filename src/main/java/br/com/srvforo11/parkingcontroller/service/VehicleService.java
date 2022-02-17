package br.com.srvforo11.parkingcontroller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.srvforo11.parkingcontroller.domain.entity.Vehicle;
import br.com.srvforo11.parkingcontroller.repository.VehicleRepository;

@Service
public class VehicleService {

	private VehicleRepository vehicleRepository;

	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	
	public List<Vehicle> list(){
		return vehicleRepository.findAll();
	}
}
