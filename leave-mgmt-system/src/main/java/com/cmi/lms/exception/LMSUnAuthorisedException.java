package com.cmi.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LMSUnAuthorisedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LMSUnAuthorisedException(String exception) {
		super(exception);
	}
}








