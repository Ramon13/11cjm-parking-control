package br.com.srvforo11.parkingcontroller.mapper;

public class VehicleDTO {

	private String registrationPlate;
	
	private String manufacturer;
	
	private String description;
	
	public VehicleDTO(String registrationPlate, String manufacturer, String description) {
		this.registrationPlate = registrationPlate;
		this.manufacturer = manufacturer;
		this.description = description;
	}
	
	public VehicleDTO() {}
	

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

	public String getVehicleInfo() {
		return String.format("[%s] %s", registrationPlate, description);
	}
	
	@Override
	public String toString() {
		return "VehicleDTO [registrationPlate=" + registrationPlate + ", manufacturer=" + manufacturer
				+ ", description=" + description + "]";
	}	
}
