package com.example.wrservice.core.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubscriptionResponse(
	long id,
	@JsonProperty("user_id")
	long userId,
	@JsonProperty("service_name")
	String serviceName
) {}