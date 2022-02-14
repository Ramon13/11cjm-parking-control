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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
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
}
