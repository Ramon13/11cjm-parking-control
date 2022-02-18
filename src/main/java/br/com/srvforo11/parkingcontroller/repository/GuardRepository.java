package br.com.srvforo11.parkingcontroller.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.srvforo11.parkingcontroller.domain.entity.Guard;
import br.com.srvforo11.parkingcontroller.domain.entity.User;

public interface GuardRepository extends JpaRepository<Guard, Long>{
	
	Optional<User> findByUsername(@Param("username") String username);
}
