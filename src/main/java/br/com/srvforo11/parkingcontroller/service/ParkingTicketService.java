package br.com.srvforo11.parkingcontroller.service;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;
import br.com.srvforo11.parkingcontroller.mapper.EntityMapper;
import br.com.srvforo11.parkingcontroller.mapper.ParkingTicketDTO;
import br.com.srvforo11.parkingcontroller.repository.ParkingTicketRepository;
import br.com.srvforo11.parkingcontroller.util.DateUtils;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class ParkingTicketService {

	private ParkingTicketRepository parkingTicketRepository;
	
	public ParkingTicketService(ParkingTicketRepository parkingTicketRepository) {
		this.parkingTicketRepository = parkingTicketRepository;
	}
	
	public List<ParkingTicketDTO> list(){
		return parkingTicketRepository.findAll(Sort.by(Order.desc("id")))
				.stream().map(ticket -> EntityMapper.fromEntityToDTO(ticket)).collect(Collectors.toList());
	}
	
	@Transactional
	public void save(ParkingTicketDTO parkingTicketDTO) {
		ParkingTicket parkingTicket = EntityMapper.fromDTOToEntity(parkingTicketDTO);
		parkingTicketRepository.save(parkingTicket);
	}
	
	public void closeTicket(Long ticketId) {
		parkingTicketRepository.closeTicket(DateUtils.now(), ticketId);
	}
}
