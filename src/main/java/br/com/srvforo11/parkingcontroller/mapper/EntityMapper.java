package br.com.srvforo11.parkingcontroller.mapper;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;

public final class EntityMapper {

	private EntityMapper() {}
	
	public static ParkingTicketDTO fromEntityToDTO(ParkingTicket parkingTicket) {
		return new ParkingTicketDTO(
				parkingTicket.getId(),
				parkingTicket.getDriver(),
				parkingTicket.getGuard(),
				parkingTicket.getVehicle(),
				parkingTicket.getStartAt(),
				parkingTicket.getEndAt());
	}
	
	public static ParkingTicket fromDTOToEntity(ParkingTicketDTO parkingTicketDTO) {
		return new ParkingTicket(
				parkingTicketDTO.getId(),
				parkingTicketDTO.getDriver(),
				parkingTicketDTO.getGuard(),
				parkingTicketDTO.getVehicle(),
				parkingTicketDTO.getStartAt(),
				parkingTicketDTO.getEndAt());
	}
}
