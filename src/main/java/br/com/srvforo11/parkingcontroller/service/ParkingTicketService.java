package br.com.srvforo11.parkingcontroller.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;
import br.com.srvforo11.parkingcontroller.domain.entity.Vehicle;
import br.com.srvforo11.parkingcontroller.exception.InvalidMileageException;
import br.com.srvforo11.parkingcontroller.mapper.EntityMapper;
import br.com.srvforo11.parkingcontroller.mapper.ParkingTicketDTO;
import br.com.srvforo11.parkingcontroller.repository.ParkingTicketRepository;
import br.com.srvforo11.parkingcontroller.util.DateUtils;

@Service
public class ParkingTicketService {

	private ParkingTicketRepository parkingTicketRepository;
	
	public ParkingTicketService(ParkingTicketRepository parkingTicketRepository) {
		this.parkingTicketRepository = parkingTicketRepository;
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
	public void save(ParkingTicketDTO parkingTicketDTO, Long guardId) throws InvalidMileageException {
		ParkingTicket parkingTicket = EntityMapper.fromDTOToEntity(parkingTicketDTO);
		validateVehicleMileage(parkingTicket.getVehicleMileage(), parkingTicket.getVehicle().getRegistrationPlate());
		parkingTicketRepository.save(parkingTicket);
	}
	
	public void closeTicket(Long ticketId) {
		parkingTicketRepository.closeTicket(DateUtils.now(), ticketId);
	}
	
	private void validateVehicleMileage(Integer mileage, String registrationPlate) throws InvalidMileageException {
		PageRequest pageable = PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"));
		ParkingTicket pt = parkingTicketRepository.findLastByVehicleRegistrationPlate(registrationPlate, pageable).stream().findFirst().get();
		
		if (mileage <= pt.getVehicleMileage())
			throw new InvalidMileageException("A quilometragem atual não pode ser menor do que a última quilometragem registrada.");
	}
}
