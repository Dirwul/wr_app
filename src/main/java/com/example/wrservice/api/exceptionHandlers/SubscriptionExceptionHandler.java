package com.example.wrservice.api.exceptionHandlers;

import com.example.wrservice.core.utils.exception.SubscriptionAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class SubscriptionExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(SubscriptionExceptionHandler.class);

	@ExceptionHandler(SubscriptionAlreadyExistsException.class)
	public ResponseEntity<Object> handleSubscriptionAlreadyExistsException(SubscriptionAlreadyExistsException e) {
		log.warn(e.getMessage());
		return ResponseEntity
			.status(HttpStatus.CONFLICT)
			.body(Map.of(
				"message", e.getMessage()
		));
	}
}
