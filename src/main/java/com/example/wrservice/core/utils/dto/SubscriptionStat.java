package com.example.wrservice.core.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubscriptionStat(
	@JsonProperty("service_name")
	String serviceName,
	long count
) {}
