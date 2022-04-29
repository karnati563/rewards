package com.rewaards.exceptions;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorDetails {

	private boolean status;
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Boolean status, Date timestamp, String message, String details) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

}
