package br.com.srvforo11.parkingcontroller.domain.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Driver {

	@Column(name = "driver_name", nullable = false)
	private String name;

	private Driver(Builder builder) {
		this.name = builder.name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Driver [name=" + name + "]";
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder{
		
		private String name;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Driver build() {
			return new Driver(this);
		}
	}
}
