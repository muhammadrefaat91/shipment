package com.springcard.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
	private String message;
	private String details;
	private String errorCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	public ExceptionResponse() {
	}

	public ExceptionResponse(String message, String details, LocalDateTime timestamp) {
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	public ExceptionResponse(String message, LocalDateTime timestamp) {
		this.message = message;
		this.timestamp = timestamp;
	}
}
