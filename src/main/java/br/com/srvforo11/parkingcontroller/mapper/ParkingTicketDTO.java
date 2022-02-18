package br.com.srvforo11.parkingcontroller.mapper;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Objects;

import br.com.srvforo11.parkingcontroller.domain.entity.Driver;
import br.com.srvforo11.parkingcontroller.domain.entity.Guard;
import br.com.srvforo11.parkingcontroller.domain.entity.Vehicle;
import br.com.srvforo11.parkingcontroller.util.DateUtils;

public class ParkingTicketDTO {

	private final String OPEN_TICKET = "openTicket";
	private final String CLOSED_TICKET = "closedTicket";
	
	private Long id;
	
	private Driver driver;
	
	private Guard guard;
	
	private Vehicle vehicle;
	
	private OffsetDateTime startAt;
	
	private OffsetDateTime endAt;
	
	private Integer vehicleMileage;
	
	private Integer distance;
	
	public ParkingTicketDTO() {}

	public ParkingTicketDTO(Long id, Driver driver, Guard guard, Vehicle vehicle, OffsetDateTime startAt,
			OffsetDateTime endAt,  Integer vehicleMileage) {
		this.id = id;
		this.driver = driver;
		this.guard = guard;
		this.vehicle = vehicle;
		this.startAt = startAt;
		this.endAt = endAt;
		this.vehicleMileage = vehicleMileage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Guard getGuard() {
		return guard;
	}

	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public OffsetDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(OffsetDateTime startAt) {
		this.startAt = startAt;
	}

	public OffsetDateTime getEndAt() {
		return endAt;
	}

	public void setEndAt(OffsetDateTime endAt) {
		this.endAt = endAt;
	}
	
	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getTicketStatus() {
		return Objects.isNull(endAt) ? OPEN_TICKET : CLOSED_TICKET;
	}
	
	public String getPrintDepartureTime() {
		return String.format("Saída em: %s", DateUtils.toDefaultFormat(startAt));
	}
	
	public String getPrintEntranceTime() {
		return String.format("Chegada em: %s", DateUtils.toDefaultFormat(endAt));
	}
	
	public String getVehicleInfo() {
		return String.format("%s [%s]", vehicle.getDescription(), vehicle.getRegistrationPlate());
	}
	
	public String getTicketDuration() {
		return String.format("Duração: %d minutos", Duration.between(startAt, endAt).toMinutes());
	}
	
	public String getDriveDistance() {
		return String.format("Distância: %s Km", Objects.isNull(distance) ? "---" : String.valueOf(distance));
	}

	public Integer getVehicleMileage() {
		return vehicleMileage;
	}

	public void setVehicleMileage(Integer vehicleMileage) {
		this.vehicleMileage = vehicleMileage;
	}

	@Override
	public String toString() {
		return "ParkingTicketDTO [id=" + id + ", driver=" + driver + ", guard=" + guard + ", vehicle=" + vehicle
				+ ", startAt=" + startAt + ", endAt=" + endAt + ", distance=" + distance + "]";
	}	
}
