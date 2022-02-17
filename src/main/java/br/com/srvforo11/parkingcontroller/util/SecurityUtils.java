package br.com.srvforo11.parkingcontroller.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class SecurityUtils {

	private SecurityUtils() {}
	
	public static String encrypt(String rawPassword) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(rawPassword);
	}
	
	public static boolean matches(String rawPassword, String encodedPassword) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.matches(rawPassword, encodedPassword);
	}
}
