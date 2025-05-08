package com.example.wrservice.api.controller;

import com.example.wrservice.core.SubscriptionService;
import com.example.wrservice.core.utils.dto.SubscriptionRequest;
import com.example.wrservice.core.utils.dto.SubscriptionResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/subscription")
public class SubscriptionForUserController {

	private final SubscriptionService subscriptionService;

	@Autowired
	public SubscriptionForUserController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@PostMapping
	public ResponseEntity<SubscriptionResponse> addSubscription(
		@PathVariable("id") long userId,
		@Valid @RequestBody SubscriptionRequest subscription
	) {
		return new ResponseEntity<>(
			subscriptionService.add(userId, subscription),
			HttpStatus.CREATED
		);
	}

	@GetMapping
	public ResponseEntity<List<SubscriptionResponse>> getSubscription (@PathVariable("id") long userId) {
		return new ResponseEntity<>(
			subscriptionService.getSubs(userId),
			HttpStatus.OK
		);
	}
}
