package com.excelr.pharma.exceptions;

/**
 * @author admin
 *
 */
public class PharmaBusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PharmaBusinessException() {
		super();
	}

	/**
	 * @param message
	 */
	public PharmaBusinessException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public PharmaBusinessException(Throwable cause) {
		super(cause);
	}

}
