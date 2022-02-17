package br.com.srvforo11.parkingcontroller.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.srvforo11.parkingcontroller.domain.entity.Guard;
import br.com.srvforo11.parkingcontroller.domain.entity.User;

public interface GuardRepository extends JpaRepository<Guard, String>{
	
	Optional<User> findByUsername(@Param("username") String username);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Guard g SET g.password = :password, g.resetCredentials = false WHERE g.id = :userId")
	void redefinePassword(@Param("password") String password, @Param("userId") Long userId);
}
