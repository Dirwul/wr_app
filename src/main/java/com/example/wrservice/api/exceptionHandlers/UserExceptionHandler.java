package com.example.wrservice.api.exceptionHandlers;

import com.example.wrservice.core.utils.exception.UserAlreadyExistsException;
import com.example.wrservice.core.utils.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
		log.warn("Пользователь уже существует");
		return ResponseEntity
			.status(HttpStatus.CONFLICT)
			.body(Map.of("message", e.getMessage()));
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
		log.warn("Пользователь не найден");
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(Map.of("message", e.getMessage()));
	}
}
