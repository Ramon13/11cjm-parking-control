package br.com.srvforo11.parkingcontroller.domain.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@MappedSuperclass
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	protected String username;

	@Column(name="password", nullable = false)
	protected String password;

	@Column(name="reset_credentials", nullable = false)
	protected Boolean resetCredentials;

	@PrePersist
	private void prePersist() {
		if (Objects.isNull(resetCredentials))
			resetCredentials = true;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean getResetCredentials() {
		return resetCredentials;
	}

	public void setResetCredentials(Boolean resetCredentials) {
		this.resetCredentials = resetCredentials;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}	
}
