package br.com.srvforo11.parkingcontroller.service;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.srvforo11.parkingcontroller.domain.entity.User;
import br.com.srvforo11.parkingcontroller.exception.InvalidPasswordException;
import br.com.srvforo11.parkingcontroller.exception.UserNotFoundException;
import br.com.srvforo11.parkingcontroller.repository.GuardRepository;
import br.com.srvforo11.parkingcontroller.repository.SupervisorRepository;
import br.com.srvforo11.parkingcontroller.util.SecurityUtils;

@Service
public class AuthService {

	private GuardRepository guardRepository;
	private SupervisorRepository supervisorRepository;

	public AuthService(GuardRepository guardRepository, SupervisorRepository supervisorRepository) {
		this.guardRepository = guardRepository;
		this.supervisorRepository = supervisorRepository;
	}

	public User login(String username, String password) throws UserNotFoundException{
		User user = getUserOrElseThrow(username);
		if (SecurityUtils.matches(password, user.getPassword()) == Boolean.FALSE)
			throw new UserNotFoundException("Password not matches" + password);
		
		return user;
	}
	
	@Transactional
	public User redefinePassword(String username, String newPassword) throws InvalidPasswordException, UserNotFoundException{
		User user = getUserOrElseThrow(username);
		
		if (newPassword.length() < 6)
			throw new InvalidPasswordException();
		
		user.setPassword(SecurityUtils.encrypt(newPassword));
		user.setResetCredentials(false);
		
		return user;
	}
	
	private User getUserOrElseThrow(String username) throws UserNotFoundException {
		Objects.nonNull(username);
		Optional<User> user = guardRepository.findByUsername(username);
		
		if (user.isPresent())
			return user.get();
				
		return supervisorRepository.findByUsername(username)
					.orElseThrow(() -> new UserNotFoundException("user not found:" + username));			
	}
}
