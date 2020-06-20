package com.pic.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateException extends RuntimeException {

	public DuplicateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
