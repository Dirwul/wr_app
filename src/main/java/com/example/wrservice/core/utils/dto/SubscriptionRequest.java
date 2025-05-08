package com.example.wrservice.core.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubscriptionRequest(
	@JsonProperty("service_name") String serviceName
) {}