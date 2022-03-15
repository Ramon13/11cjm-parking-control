package br.com.srvforo11.parkingcontroller.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;
import br.com.srvforo11.parkingcontroller.domain.entity.Vehicle;
import br.com.srvforo11.parkingcontroller.exception.GuardNotFoundException;
import br.com.srvforo11.parkingcontroller.exception.InvalidMileageException;
import br.com.srvforo11.parkingcontroller.mapper.EntityMapper;
import br.com.srvforo11.parkingcontroller.mapper.ParkingTicketDTO;
import br.com.srvforo11.parkingcontroller.repository.ParkingTicketRepository;
import br.com.srvforo11.parkingcontroller.util.DateUtils;

@Service
public class ParkingTicketService {

	private ParkingTicketRepository parkingTicketRepository;
	private GuardService guardService;
	
	public ParkingTicketService(ParkingTicketRepository parkingTicketRepository, GuardService guardService) {
		this.parkingTicketRepository = parkingTicketRepository;
		this.guardService = guardService;
	}
	
	public List<ParkingTicketDTO> list(){
		List<ParkingTicketDTO> tickets = new ArrayList<>();
		
		tickets.addAll(parkingTicketRepository.findAllUnfinished()
			.stream().map(ticket -> EntityMapper.fromEntityToDTO(ticket)).collect(Collectors.toList()) );
		
		tickets.addAll(parkingTicketRepository.findAllFinished()
				.stream().map(ticket -> EntityMapper.fromEntityToDTO(ticket)).collect(Collectors.toList()) );
		
		setDistances(tickets);
		return tickets;
	}

	private void setDistances(List<ParkingTicketDTO> tickets) {
		Map<Vehicle, Integer> vehicleMapMileage = new HashMap<>();
		Integer mileage;
		for (ParkingTicketDTO ticket : tickets) {
			mileage = vehicleMapMileage.get(ticket.getVehicle());
			
			if ( !Objects.isNull(mileage) && !Objects.isNull(ticket.getVehicleMileage()) )	
				ticket.setDistance(Math.round(mileage - ticket.getVehicleMileage()));
			
			vehicleMapMileage.put(ticket.getVehicle(), ticket.getVehicleMileage());
		}
	}
	
	@Transactional
	public void save(ParkingTicketDTO parkingTicketDTO, Long guardId) throws InvalidMileageException, GuardNotFoundException {
		ParkingTicket parkingTicket = EntityMapper.fromDTOToEntity(parkingTicketDTO);
		validateVehicleMileage(parkingTicket.getVehicleMileage(), parkingTicket.getVehicle().getRegistrationPlate());
		parkingTicket.setOpenedBy(guardService.get(guardId));
		
		parkingTicketRepository.save(parkingTicket);
	}
	
	public void closeTicket(Long ticketId, Long guardId) {	
		parkingTicketRepository.closeTicket(DateUtils.now(), ticketId, guardId);
	}
	
	private void validateVehicleMileage(Integer mileage, String registrationPlate) throws InvalidMileageException {
		if (Objects.isNull(mileage))
			return;
		
		PageRequest pageable = PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"));
		Optional<ParkingTicket> parkingTicket = 
				parkingTicketRepository.findLastByVehicleRegistrationPlate(registrationPlate, pageable).stream().findFirst();
		
		if (parkingTicket.isPresent() && mileage <= parkingTicket.get().getVehicleMileage())
			throw new InvalidMileageException("A quilometragem atual não pode ser menor do que a última quilometragem registrada.");
	}
	
	public boolean checkUpdateTicket(LocalDateTime lastCheck) {
		Long countResults = parkingTicketRepository.findStartAtOrEndAtAfter(OffsetDateTime.of(lastCheck, DateUtils.DEFAULT_TIMEZONE));
		return ( countResults > 0 );
	}
}
