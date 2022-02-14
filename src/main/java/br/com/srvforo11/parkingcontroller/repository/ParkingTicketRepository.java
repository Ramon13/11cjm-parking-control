package br.com.srvforo11.parkingcontroller.repository;

import br.com.srvforo11.parkingcontroller.domain.entity.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long>{}
