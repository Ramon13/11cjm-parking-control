package br.com.srvforo11.parkingcontroller.repository;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long>{
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE ParkingTicket pt SET pt.endAt = :endAt, pt.closedBy.id = :guardId WHERE pt.id = :ticketId")
	void closeTicket(
		@Param("endAt") OffsetDateTime endAt, 
		@Param("ticketId") Long ticketId,
		@Param("guardId") Long guardId
	);
	
	@Query("FROM ParkingTicket p WHERE p.vehicle.registrationPlate = :vehiclePlate")
	Page<ParkingTicket> findLastByVehicleRegistrationPlate(
			@Param("vehiclePlate") String registrationPlate, Pageable pageable);
	
	@Query("SELECT COUNT(p) FROM ParkingTicket p WHERE p.startAt > :lastCheckDate OR p.endAt > :lastCheckDate")
	Long findStartAtOrEndAtAfter(@Param("lastCheckDate") OffsetDateTime lastCheckDate);
	
	@Query("FROM ParkingTicket p")
	Page<ParkingTicket> findAll(Pageable pageable);
	
	@Query("FROM ParkingTicket p WHERE p.endAt != null ORDER BY p.id DESC")
	List<ParkingTicket> findAllFinished();
	
	@Query("FROM ParkingTicket p WHERE p.endAt = null ORDER BY p.id DESC")
	List<ParkingTicket> findAllUnfinished();
}
