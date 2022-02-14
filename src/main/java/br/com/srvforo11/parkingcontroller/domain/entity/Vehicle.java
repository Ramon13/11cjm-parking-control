package br.com.srvforo11.parkingcontroller.domain.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Vehicle {

	@Column(name = "vehicle_name", nullable = false)
	private String name;
	
	@Column(name = "registration_plate", nullable = false)
	private String registrationPlate;

	private Vehicle(Builder builder) {
		this.name = builder.name;
		this.registrationPlate = builder.registrationPlate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, registrationPlate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(name, other.name) && Objects.equals(registrationPlate, other.registrationPlate);
	}

	@Override
	public String toString() {
		return "Vehicle [name=" + name + ", registrationPlate=" + registrationPlate + "]";
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder{
		
		private String name;
		private String registrationPlate;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder registrationPlate(String registrationPlate) {
			this.registrationPlate = registrationPlate;
			return this;
		}
		
		public Vehicle build() {
			return new Vehicle(this);
		}
	}
}
