package br.com.srvforo11.parkingcontroller.infrastructure.controller;

import br.com.srvforo11.parkingcontroller.mapper.ParkingTicketDTO;
import br.com.srvforo11.parkingcontroller.service.ParkingTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class ParkingTicketController {

	private final ParkingTicketService parkingTicketService;
	
	public ParkingTicketController(ParkingTicketService parkingTicketService) {
		this.parkingTicketService = parkingTicketService;
	}

	@GetMapping("/list")
	public String index(@ModelAttribute("parkingTicket") ParkingTicketDTO parkingTicketDTO, Model model) {
		model.addAttribute("tickets", parkingTicketService.list());
		model.addAttribute("parkingTicket", new ParkingTicketDTO());
		return "ticket/index";
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity save(@ModelAttribute("parkingTicket") ParkingTicketDTO parkingTicketDTO) {
		parkingTicketService.save(parkingTicketDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
