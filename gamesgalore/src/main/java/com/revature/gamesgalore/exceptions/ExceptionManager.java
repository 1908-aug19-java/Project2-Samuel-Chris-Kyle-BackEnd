package com.revature.gamesgalore.exceptions;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionManager {

	/**
	 * A String representing the message to show when the A HTTP response object has its status set to BAD_REQUEST.
	 */
	private static final String BODY_INVALID = "Bad request from malformed data supplied";
	/**
	 * A String representing the message to show when the A HTTP response object has its status set to NOT_FOUND.
	 */
	private static final String NOT_FOUND = "The requested data was not found with the specified ID";
	/**
	 * A String representing the message to show when the A HTTP response object has its status set to INTERNAL_SERVER_ERROR.
	 */
	private static final String UNEXPECTED_ERROR = "An unexpected server error occurred";

	/**
	 * 
	 * @return A supplier that returns a ResponseStatusException with a status of 400 BAD_REQUEST, when its get method is invoked.
	 */
	public static Supplier<ResponseStatusException> supplierThrows400Exception() {
		return () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, BODY_INVALID);
	}
	
	/**
	 * 
	 * @return A supplier that returns a ResponseStatusException with a status of 404 NOT_FOUND, when its get method is invoked.
	 */
	public static Supplier<ResponseStatusException> supplierThrows404Exception() {
		return () -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND);
	}

	/**
	 * 
	 * @return A supplier that returns a ResponseStatusException with a status of 500 INTERNAL_SERVER_ERROR, when its get method is invoked.
	 */
	public static Supplier<ResponseStatusException> supplierThrows500Exception() {
		return () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR);
	}
	
	private ExceptionManager() {}
	
}

