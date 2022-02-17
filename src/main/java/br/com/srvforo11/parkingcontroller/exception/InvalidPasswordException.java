package br.com.srvforo11.parkingcontroller.exception;

public class InvalidPasswordException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPasswordException(String message) {
		super(message);
	}

	public InvalidPasswordException(Throwable cause) {
		super(cause);
	}

	public InvalidPasswordException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
