package br.com.srvforo11.parkingcontroller.exception;

public class InvalidMileageException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidMileageException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidMileageException(String message) {
		super(message);
	}

	public InvalidMileageException(Throwable cause) {
		super(cause);
	}

	public InvalidMileageException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
