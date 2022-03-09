package br.com.srvforo11.parkingcontroller.domain.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.srvforo11.parkingcontroller.util.DateUtils;

@Entity
@Table(name = "parking_ticket")
public class ParkingTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "driver_cpf", nullable = false)
	private Driver driver;
	
	@ManyToOne
	@JoinColumn(name = "opened_guard_id", nullable = false)
	private Guard openedBy;
	
	@ManyToOne
	@JoinColumn(name = "closed_guard_id", nullable = true)
	private Guard closedBy;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_plate", nullable = false)
	private Vehicle vehicle;
	
	@Column(name = "start_at", nullable = false)
	private OffsetDateTime startAt;
	
	@Column(name = "end_at")
	private OffsetDateTime endAt;
	
	@Column(name = "vehicle_mileage", nullable = false)
	private Integer vehicleMileage;
	
	public ParkingTicket(Long id, Driver driver, Guard openedBy, Guard closedBy, Vehicle vehicle,
			OffsetDateTime startAt, OffsetDateTime endAt, Integer vehicleMileage) {
		this.id = id;
		this.driver = driver;
		this.openedBy = openedBy;
		this.closedBy = closedBy;
		this.vehicle = vehicle;
		this.startAt = startAt;
		this.endAt = endAt;
		this.vehicleMileage = vehicleMileage;
	}

	public ParkingTicket() {}
	
	@PrePersist
	public void prePersist() {
		if (Objects.isNull(id)) {
			startAt = DateUtils.now();
			endAt = null;
		}
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

	public Guard getOpenedBy() {
		return openedBy;
	}

	public void setOpenedBy(Guard openedBy) {
		this.openedBy = openedBy;
	}

	public Guard getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(Guard closedBy) {
		this.closedBy = closedBy;
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

	public Integer getVehicleMileage() {
		return vehicleMileage;
	}

	public void setVehicleMileage(Integer vehicleMileage) {
		this.vehicleMileage = vehicleMileage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingTicket other = (ParkingTicket) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ParkingTicket [id=" + id + ", driver=" + driver + ", openedBy=" + openedBy + ", closedBy=" + closedBy
				+ ", vehicle=" + vehicle + ", startAt=" + startAt + ", endAt=" + endAt + ", vehicleMileage="
				+ vehicleMileage + "]";
	}
}
