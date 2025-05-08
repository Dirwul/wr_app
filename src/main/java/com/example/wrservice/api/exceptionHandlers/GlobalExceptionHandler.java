package com.example.wrservice.api.exceptionHandlers;

import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("Method Argument Not Valid Exception: {}", e.getMessage());

		List<Map<String, Object>> errors = e.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(fieldError -> Map.of(
				"field", fieldError.getField(),
				"rejectedValue", fieldError.getRejectedValue(),
				"message", fieldError.getDefaultMessage()
			))
			.toList();

		return ResponseEntity
			.badRequest()
			.body(Map.of(
				"message", "Validation failed",
				"errors", errors
			));
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> handleSQLException(SQLException e) {
		log.error("SQL Exception: {}", e.getMessage());
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(Map.of("message", "Database error"));
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> handleDataAccessException(DataAccessException e) {
		log.error("Data Access Exception: {}", e.getMessage());
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(Map.of("message", "Database access error"));
	}

	@ExceptionHandler(LazyInitializationException.class)
	public ResponseEntity<?> handleLazyInitializationException(LazyInitializationException e) {
		log.error("Lazy Initialization Exception: {}", e.getMessage());
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(Map.of("message", "Database error"));
	}
}
