package br.com.srvforo11.parkingcontroller.domain.entity;

import br.com.srvforo11.parkingcontroller.util.DateUtils;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "parking_ticket")
public class ParkingTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Driver driver;
	
	@Embedded
	private Guard guard;
	
	@Embedded
	private Vehicle vehicle;
	
	@Column(name = "start_at", nullable = false)
	private OffsetDateTime startAt;
	
	@Column(name = "end_at")
	private OffsetDateTime endAt;
	
	public ParkingTicket(Long id, Driver driver, Guard guard, Vehicle vehicle, OffsetDateTime startAt,
	        OffsetDateTime endAt) {
		this.id = id;
		this.driver = driver;
		this.guard = guard;
		this.vehicle = vehicle;
		this.startAt = startAt;
		this.endAt = endAt;
	}
	
	public ParkingTicket() {}
	
	@PrePersist
	private void prePersist() {
		if (Objects.isNull(startAt))
			startAt = DateUtils.now();
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

	@Override
	public int hashCode() {
		return Objects.hash(id, startAt);
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
		return Objects.equals(id, other.id) && Objects.equals(startAt, other.startAt);
	}
	
	@Override
	public String toString() {
		return "ParkingTicket [id=" + id + ", startAt=" + startAt + ", endAt=" + endAt + "]";
	}
}
