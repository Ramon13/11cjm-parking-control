package br.com.srvforo11.parkingcontroller.mapper;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;
import br.com.srvforo11.parkingcontroller.domain.entity.Vehicle;

public final class EntityMapper {

	private EntityMapper() {}
	
	public static ParkingTicketDTO fromEntityToDTO(ParkingTicket parkingTicket) {
		return new ParkingTicketDTO(
				parkingTicket.getId(),
				parkingTicket.getDriver(),
				parkingTicket.getOpenedBy(),
				parkingTicket.getClosedBy(),
				parkingTicket.getVehicle(),
				parkingTicket.getStartAt(),
				parkingTicket.getEndAt(),
				parkingTicket.getVehicleMileage(),
				null);
	}
	
	public static ParkingTicket fromDTOToEntity(ParkingTicketDTO parkingTicketDTO) {
		return new ParkingTicket(
				parkingTicketDTO.getId(),
				parkingTicketDTO.getDriver(),
				parkingTicketDTO.getOpenedBy(),
				parkingTicketDTO.getClosedBy(),
				parkingTicketDTO.getVehicle(),
				parkingTicketDTO.getStartAt(),
				parkingTicketDTO.getEndAt(),
				parkingTicketDTO.getVehicleMileage());
	}
	
	public static VehicleDTO fromEntityToDTO(Vehicle vehicle) {
		return new VehicleDTO(vehicle.getRegistrationPlate(), vehicle.getManufacturer(), vehicle.getDescription());
	}
}
