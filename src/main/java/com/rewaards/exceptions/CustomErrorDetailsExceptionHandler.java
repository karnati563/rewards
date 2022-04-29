package com.rewaards.exceptions;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomErrorDetailsExceptionHandler {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ CustomerNotFoundException.class })
	public @ResponseBody ErrorDetails handleEPATException(final CustomerNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(false, new Date(), ex.getMessage().toString(),
				request.getDescription(false));
		return errorDetails;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorDetails handleAllException(final Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(false, new Date(), "Internal Server Eror :: " + ex.getMessage(),
				request.getDescription(false));
		return errorDetails;
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorDetails handleJDBCException(final Exception ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(false, new Date(), "DataBase Error",
				request.getDescription(false));
		return errorDetails;
	}
}