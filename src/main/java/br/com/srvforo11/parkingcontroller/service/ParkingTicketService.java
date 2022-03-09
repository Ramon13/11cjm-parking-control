package br.com.srvforo11.parkingcontroller.service;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;
import br.com.srvforo11.parkingcontroller.domain.entity.Vehicle;
import br.com.srvforo11.parkingcontroller.exception.GuardNotFoundException;
import br.com.srvforo11.parkingcontroller.exception.InvalidMileageException;
import br.com.srvforo11.parkingcontroller.mapper.EntityMapper;
import br.com.srvforo11.parkingcontroller.mapper.ParkingTicketDTO;
import br.com.srvforo11.parkingcontroller.repository.ParkingTicketRepository;
import br.com.srvforo11.parkingcontroller.util.DateUtils;
import java.time.OffsetDateTime;
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
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class ParkingTicketService {

	private ParkingTicketRepository parkingTicketRepository;
	private GuardService guardService;
	
	public ParkingTicketService(ParkingTicketRepository parkingTicketRepository, GuardService guardService) {
		this.parkingTicketRepository = parkingTicketRepository;
		this.guardService = guardService;
	}
	
	public List<ParkingTicketDTO> list(){
		List<ParkingTicketDTO> tickets = parkingTicketRepository.findAll(Sort.by(Order.desc("id")))
				.stream().map(ticket -> EntityMapper.fromEntityToDTO(ticket)).collect(Collectors.toList());
		
		setDistances(tickets);
		return tickets;
	}

	private void setDistances(List<ParkingTicketDTO> tickets) {
		Map<Vehicle, Integer> vehicleMapMileage = new HashMap<>();
		Integer mileage;
		for (ParkingTicketDTO ticket : tickets) {
			mileage = vehicleMapMileage.get(ticket.getVehicle());
			
			if (!Objects.isNull(mileage))	
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
		PageRequest pageable = PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"));
		Optional<ParkingTicket> pt = parkingTicketRepository.findLastByVehicleRegistrationPlate(registrationPlate, pageable).stream().findFirst();
		
		if (pt.isPresent() && mileage <= pt.get().getVehicleMileage())
			throw new InvalidMileageException("A quilometragem atual não pode ser menor do que a última quilometragem registrada.");
	}
	
	public boolean checkUpdateTicket(OffsetDateTime lastCheck) {
		return !parkingTicketRepository.findStartAtOrEndAtAfter(lastCheck).isEmpty();
	}
}
