package com.happiestminds.asi.exception;

import java.io.Serializable;

/**
 * @author shruti.mishra
 *
 */
public class AsiException extends RuntimeException implements Serializable {

	
	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = -622636882097529634L;
	
	public AsiException() {
		super();
	}

	public AsiException(String message) {
		super(message);
	}
	
	public AsiException(String message,Throwable cause) {
		super(message,cause);
	}
	
}
