package com.rewaards.exceptions;

@SuppressWarnings("serial")
public class AppSystemException extends Exception {

	public AppSystemException(String message) {
		super(message);
	}

	public AppSystemException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
