package com.revature.gamesgalore.exceptions;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionManager {

	/**
	 * A String representing the message to show when the A HTTP response object has
	 * its status set to BAD_REQUEST.
	 */
	public static final String BODY_INVALID = "Bad request body. Malformed or invalid data was supplied";

	/**
	 * A String representing the message to show when the A HTTP response object has
	 * its status set to BAD_REQUEST.
	 */
	public static final String VALIDATION_FAILED = "Request body has failed validations";
	/**
	 * A String representing the message to show when the A HTTP response object has
	 * its status set to NOT_FOUND.
	 */
	public static final String NOT_FOUND = "The requested data was not found.";
	/**
	 * A String representing the message to show when the A HTTP response object has
	 * its status set to INTERNAL_SERVER_ERROR.
	 */
	public static final String UNEXPECTED_ERROR = "An unexpected server error occurred";

	public static Supplier<ResponseStatusException> getRSE(HttpStatus status, String message) {

		switch (status) {
		case BAD_REQUEST:
			return () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
		case FORBIDDEN:
			return () -> new ResponseStatusException(HttpStatus.FORBIDDEN, message);
		case NOT_FOUND:
			return () -> new ResponseStatusException(HttpStatus.NOT_FOUND, message);
		case UNAUTHORIZED:
			return () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, message);
		case INTERNAL_SERVER_ERROR:
			return () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
		default:
			break;
		}
		return null;

	}

	private ExceptionManager() {
	}

}
