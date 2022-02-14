package br.com.srvforo11.parkingcontroller.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@Column(name = "registration_plate", nullable = false, unique = true)
	private String registrationPlate;
	
	@Column(name = "manufacturer", nullable = false)
	private String manufacturer;
	
	@Column(name = "description", nullable = false)
	private String description;

	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(registrationPlate);
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
		return Objects.equals(registrationPlate, other.registrationPlate);
	}

	@Override
	public String toString() {
		return "Vehicle [registrationPlate=" + registrationPlate + ", manufacturer=" + manufacturer + ", description="
				+ description + "]";
	}
}
