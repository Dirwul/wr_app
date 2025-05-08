package com.example.wrservice.core.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SubscriptionAlreadyExistsException extends RuntimeException {

	public SubscriptionAlreadyExistsException(String message) {
		super(message);
	}
}
