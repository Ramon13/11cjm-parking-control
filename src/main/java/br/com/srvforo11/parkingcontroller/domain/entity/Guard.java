package br.com.srvforo11.parkingcontroller.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "guard")
public class Guard extends User{
	
	@Id
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;
	
	@Column(name = "name", nullable = false)
	private String name;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guard other = (Guard) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Guard [cpf=" + cpf + ", name=" + name + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + "]";
	}
}
