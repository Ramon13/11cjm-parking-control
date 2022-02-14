package br.com.srvforo11.parkingcontroller.domain.entity;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

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
	
	@CreationTimestamp
	@Column(name = "start_at", nullable = false)
	private OffsetDateTime startAt;
	
	@Column(name = "end_at", nullable = false)
	private OffsetDateTime endAt;

	private ParkingTicket(Builder builder) {
		this.id = builder.id;
		this.driver = builder.driver;
		this.guard = builder.guard;
		this.vehicle = builder.vehicle;
		this.startAt = builder.startAt;
		this.endAt = builder.endAt;
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

	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder {
		
		private Long id;
		private Driver driver;
		private Guard guard;
		private Vehicle vehicle;
		private OffsetDateTime startAt;		
		private OffsetDateTime endAt;
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder driver(Driver driver) {
			this.driver = driver;
			return this;
		}
		
		public Builder guard(Guard guard) {
			this.guard = guard;
			return this;
		}
		
		public Builder vehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
			return this;
		}
		
		public Builder startAt(OffsetDateTime startAt) {
			this.startAt = startAt;
			return this;
		}
		
		public Builder endAt(OffsetDateTime endAt) {
			this.endAt = endAt;
			return this;
		}
		
		public ParkingTicket build() {
			return new ParkingTicket(this);
		}
	}
}
