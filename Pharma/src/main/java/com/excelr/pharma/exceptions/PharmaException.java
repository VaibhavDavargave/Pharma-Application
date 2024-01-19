package com.excelr.pharma.exceptions;

/**
 * @author admin
 *
 */
public class PharmaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PharmaException() {
		super();
	}

	/**
	 * @param message
	 */
	public PharmaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PharmaException(Throwable cause) {
		super(cause);
	}

}
