package br.com.srvforo11.parkingcontroller.service;

import org.springframework.stereotype.Service;

import br.com.srvforo11.parkingcontroller.domain.entity.Guard;
import br.com.srvforo11.parkingcontroller.exception.GuardNotFoundException;
import br.com.srvforo11.parkingcontroller.repository.GuardRepository;

@Service
public class GuardService {

	private GuardRepository guardRepository;

	public GuardService(GuardRepository guardRepository) {
		this.guardRepository = guardRepository;
	}
	
	public Guard get(Long guardId) throws GuardNotFoundException{
		return guardRepository.findById(guardId).orElseThrow(() -> new GuardNotFoundException("guard not found " + guardId));
	}
}
