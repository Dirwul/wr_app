package com.example.wrservice.core.utils.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
	@NotBlank
	@Size(min = 2, max = 255)
	String username,

	@Email
	String email
) {
}
