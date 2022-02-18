package br.com.srvforo11.parkingcontroller.infrastructure.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.srvforo11.parkingcontroller.domain.entity.User;
import br.com.srvforo11.parkingcontroller.exception.InvalidPasswordException;
import br.com.srvforo11.parkingcontroller.exception.UserNotFoundException;
import br.com.srvforo11.parkingcontroller.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/login")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "auth/index";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, Errors errors, Model model, HttpSession httpSession) {
		if (!errors.hasErrors()) {
			try {
				User loggedUser = authService.login(user.getUsername(), user.getPassword());
				httpSession.setAttribute("loggedUser", loggedUser);
				
				return "redirect:/ticket/list";
			} catch (UserNotFoundException e) {
				errors.rejectValue("username", null, "Credenciais inválidas");
			}
		}
		
		return "auth/index";
	}
	
	@GetMapping("/logoff")
	public String logoff(HttpSession session) {
		session.removeAttribute("loggedUser");
		return "redirect:/auth/login";
	}
	
	@GetMapping("/redefine")
	public String redefinePasswordHome(Model model) {
		model.addAttribute("user", new User());
		return "auth/redef-pass";
	}
	
	@PostMapping("/redefine")
	public String redefinePassword(@Valid @ModelAttribute("user") User user, Errors errors, Model model, HttpSession session) 
			throws UserNotFoundException { 
		try {
			User loggedUser = (User) session.getAttribute("loggedUser");
			
			loggedUser = authService.redefinePassword(loggedUser.getUsername(), user.getPassword());
			session.setAttribute("loggedUser", loggedUser);
			
			return "redirect:/ticket/list";
		} catch (InvalidPasswordException e) {
			errors.rejectValue("password", null, "A senha deve conter 6 ou mais caracteres");
		}
		
		return "auth/redef-pass";
	}
}
