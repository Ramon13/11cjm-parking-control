package br.com.srvforo11.parkingcontroller.infrastructure.controller;

import br.com.srvforo11.parkingcontroller.mapper.EntityMapper;
import br.com.srvforo11.parkingcontroller.mapper.ParkingTicketDTO;
import br.com.srvforo11.parkingcontroller.service.DriverService;
import br.com.srvforo11.parkingcontroller.service.ParkingTicketService;
import br.com.srvforo11.parkingcontroller.service.VehicleService;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ticket")
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
	
	@GetMapping("/list")
	public String index(@ModelAttribute("parkingTicket") ParkingTicketDTO parkingTicketDTO, Model model) {
		model.addAttribute("tickets", parkingTicketService.list());
		model.addAttribute("drivers", driverService.list());
		model.addAttribute("vehicles", 
				vehicleService.list().stream().map(v -> EntityMapper.fromEntityToDTO(v)).collect(Collectors.toList()));
		model.addAttribute("parkingTicket", new ParkingTicketDTO());
		return "ticket/index";
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/save")
	public ResponseEntity save(@ModelAttribute("parkingTicket") ParkingTicketDTO parkingTicketDTO) {
		parkingTicketService.save(parkingTicketDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/close")
	public String close(@RequestParam("ticketId") Long id) {
		parkingTicketService.closeTicket(id);
		return "redirect:/ticket/list";
	}
}
