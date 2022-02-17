package br.com.srvforo11.parkingcontroller.service;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.srvforo11.parkingcontroller.domain.entity.User;
import br.com.srvforo11.parkingcontroller.exception.InvalidPasswordException;
import br.com.srvforo11.parkingcontroller.exception.UserNotFoundException;
import br.com.srvforo11.parkingcontroller.repository.GuardRepository;
import br.com.srvforo11.parkingcontroller.util.SecurityUtils;

@Service
public class AuthService {

	private GuardRepository guardRepository;

	public AuthService(GuardRepository guardRepository) {
		this.guardRepository = guardRepository;
	}

	public User login(String username, String password) throws UserNotFoundException{
		User user = guardRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user not found: " + username));
		
		if (SecurityUtils.matches(password, user.getPassword()) == Boolean.FALSE)
			throw new UserNotFoundException("Password not matches" + password);
		
		return user;
	}
	
	@Transactional
	public void redefinePassword(Long userId, String newPassword) throws InvalidPasswordException {
		Objects.nonNull(userId);
		if (newPassword.length() < 6)
			throw new InvalidPasswordException();
		
		guardRepository.redefinePassword(SecurityUtils.encrypt(newPassword), userId);
	}
	
}
