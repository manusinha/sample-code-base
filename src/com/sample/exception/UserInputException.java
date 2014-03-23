package com.sample.exception;

/**
 * Exception thrown in case user prompt is missing
 * 
 * @author manu sinha
 * 
 */
public class UserInputException extends Exception {

	private static final long serialVersionUID = 7965838862423446577L;

	public UserInputException(String message) {
		super(message);
	}
}