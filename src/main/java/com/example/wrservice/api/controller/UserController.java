package com.example.wrservice.api.controller;

import com.example.wrservice.core.UserService;
import com.example.wrservice.core.utils.dto.UserRequest;
import com.example.wrservice.core.utils.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user) {
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(
		@PathVariable long id,
		@Valid @RequestBody UserRequest user
	) {
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
