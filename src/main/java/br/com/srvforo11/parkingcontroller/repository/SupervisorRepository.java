package br.com.srvforo11.parkingcontroller.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.srvforo11.parkingcontroller.domain.entity.Supervisor;
import br.com.srvforo11.parkingcontroller.domain.entity.User;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long>{
	
	Optional<User> findByUsername(@Param("username") String username);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Supervisor s SET s.password = :password, s.resetCredentials = false WHERE s.id = :userId")
	void redefinePassword(@Param("password") String password, @Param("userId") Long userId);
}
