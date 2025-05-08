package com.example.wrservice.core.utils.dto;

import java.util.List;

public record UserResponse(
	long id,
	String username,
	String email,
	List<String> subscriptions
) {
}
