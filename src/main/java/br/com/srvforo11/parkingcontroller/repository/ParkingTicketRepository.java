package br.com.srvforo11.parkingcontroller.repository;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;
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
	
	@Query("FROM ParkingTicket p WHERE p.startAt > :date OR p.endAt > :date")
	List<ParkingTicket> findStartAtOrEndAtAfter(@Param("date") OffsetDateTime date);
}
