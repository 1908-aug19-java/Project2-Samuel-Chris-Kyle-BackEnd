package com.revature.gamesgalore.exceptions;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionManager {

	/**
	 * A String representing the message to show when the A HTTP response object has its status set to BAD_REQUEST.
	 */
	private static final String bodyInvalid = "Bad request from malformed data supplied";
	/**
	 * A String representing the message to show when the A HTTP response object has its status set to NOT_FOUND.
	 */
	private static final String notFound = "The requested data was not found with the specified ID";
	/**
	 * A String representing the message to show when the A HTTP response object has its status set to INTERNAL_SERVER_ERROR.
	 */
	private static final String unexpectedError = "An unexpected server error occurred";

	/**
	 * 
	 * @return A supplier that returns a ResponseStatusException with a status of 400 BAD_REQUEST, when its get method is invoked.
	 */
	public static Supplier<ResponseStatusException> supplierThrows400Exception() {
		return () -> {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, bodyInvalid);
		};
	}
	
	/**
	 * 
	 * @return A supplier that returns a ResponseStatusException with a status of 404 NOT_FOUND, when its get method is invoked.
	 */
	public static Supplier<ResponseStatusException> supplierThrows404Exception() {
		return () -> {
			return new ResponseStatusException(HttpStatus.NOT_FOUND, notFound);
		};
	}

	/**
	 * 
	 * @return A supplier that returns a ResponseStatusException with a status of 500 INTERNAL_SERVER_ERROR, when its get method is invoked.
	 */
	public static Supplier<ResponseStatusException> supplierThrows500Exception() {
		return () -> {
			return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, unexpectedError);
		};
	}
	
}

