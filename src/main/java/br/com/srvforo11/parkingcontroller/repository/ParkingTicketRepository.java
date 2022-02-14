package br.com.srvforo11.parkingcontroller.repository;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long>{
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE ParkingTicket pt SET pt.endAt = :endAt WHERE pt.id = :ticketId")
	void closeTicket(@Param("endAt") OffsetDateTime endAt, @Param("ticketId") Long ticketId);
}
