package br.com.srvforo11.parkingcontroller.exception;

public class GuardNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public GuardNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GuardNotFoundException(String message) {
		super(message);
	}

	public GuardNotFoundException(Throwable cause) {
		super(cause);
	}

	public GuardNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
