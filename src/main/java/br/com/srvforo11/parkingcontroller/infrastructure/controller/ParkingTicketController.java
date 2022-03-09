package br.com.srvforo11.parkingcontroller.infrastructure.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.srvforo11.parkingcontroller.domain.entity.Guard;
import br.com.srvforo11.parkingcontroller.domain.entity.User;
import br.com.srvforo11.parkingcontroller.exception.GuardNotFoundException;
import br.com.srvforo11.parkingcontroller.exception.InvalidMileageException;
import br.com.srvforo11.parkingcontroller.mapper.EntityMapper;
import br.com.srvforo11.parkingcontroller.mapper.ParkingTicketDTO;
import br.com.srvforo11.parkingcontroller.service.DriverService;
import br.com.srvforo11.parkingcontroller.service.ParkingTicketService;
import br.com.srvforo11.parkingcontroller.service.VehicleService;

@Controller
@RequestMapping("/ticket")
@SessionAttributes("lastCheck")
public class ParkingTicketController {

	private final ParkingTicketService parkingTicketService;
	private final DriverService driverService;
	private final VehicleService vehicleService;
	
	public ParkingTicketController(ParkingTicketService parkingTicketService, DriverService driverService,
			VehicleService vehicleService) {
		this.parkingTicketService = parkingTicketService;
		this.driverService = driverService;
		this.vehicleService = vehicleService;
	}
	
	@ModelAttribute("lastCheck")
	public LocalDateTime initLastCheck() {
		return LocalDateTime.now();
	}
	
	@GetMapping("/list")
	public String index(@ModelAttribute("parkingTicket") ParkingTicketDTO parkingTicketDTO, Model model, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		
		model.addAttribute("tickets", parkingTicketService.list());
		model.addAttribute("drivers", driverService.list());
		model.addAttribute("vehicles", 
				vehicleService.list().stream().map(v -> EntityMapper.fromEntityToDTO(v)).collect(Collectors.toList()));
		model.addAttribute("parkingTicket", new ParkingTicketDTO());
		model.addAttribute("isGuardUser", (user instanceof Guard));
		return "ticket/index";
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/save")
	public ResponseEntity save(@ModelAttribute("parkingTicket") ParkingTicketDTO parkingTicketDTO, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		
		try {
			parkingTicketService.save(parkingTicketDTO, user.getId());
		} catch (InvalidMileageException | GuardNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/close")
	public String close(@RequestParam("ticketId") Long id, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		
		parkingTicketService.closeTicket(id, user.getId());
		return "redirect:/ticket/list";
	}
	
	@GetMapping("/update/listener")
	public ResponseEntity<String> ticketChanged(@ModelAttribute("lastCheck") LocalDateTime lastCheck, Model model){
		if ( parkingTicketService.checkUpdateTicket(lastCheck) ) {
			model.addAttribute("lastCheck", LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	}
}
