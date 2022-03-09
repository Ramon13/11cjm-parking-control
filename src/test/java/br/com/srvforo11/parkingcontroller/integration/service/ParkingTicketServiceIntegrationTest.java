package br.com.srvforo11.parkingcontroller.integration.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import br.com.srvforo11.parkingcontroller.service.ParkingTicketService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ParkingTicketServiceIntegrationTest {

	@Autowired
	private ParkingTicketService victim;
	
	@Test
	void testUpdateCheck() {
		victim.checkUpdateTicket(LocalDateTime.now());
	}
}
