package com.example.wrservice.api.controller;

import com.example.wrservice.core.SubscriptionService;
import com.example.wrservice.core.utils.dto.SubscriptionStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	private final SubscriptionService subscriptionService;

	@Autowired
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@DeleteMapping("/{sub_id}")
	public ResponseEntity<Void> deleteSubscription(@PathVariable("sub_id") long id) {
		subscriptionService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/top")
	public ResponseEntity<List<SubscriptionStat>> getTopSubscriptions() {
		return new ResponseEntity<>(subscriptionService.findTopSubscriptions(), HttpStatus.OK);
	}
}
