package com.example.wrservice.core.utils.dto;

import com.example.wrservice.data.model.Subscription;
import com.example.wrservice.data.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DTOMapper {

	//  USER MAPPING

	// dto -> entity
	User toEntity(UserRequest userRequest);

	// entity -> dto
	UserResponse toResponse(User user);

	// SUBSCRIPTION MAPPING

	// dto -> entity
	Subscription toEntity(SubscriptionRequest subscriptionRequest);

	// entity -> dto
	@Mapping(source = "user.id", target = "userId")
	SubscriptionResponse toResponse(Subscription subscription);

	// SUBSCRIPTIONS LIST MAPPING

	// List<Sub> -> List<sub.serviceName>
	default List<String> subscriptionsToNameList(List<Subscription> subs) {
		return subs.stream()
			.map(Subscription::getServiceName)
			.toList();
	}

	// List<Sub> -> List<SubResponse>
	default List<SubscriptionResponse> subscriptionsToResponseList(List<Subscription> subs) {
		return subs.stream()
			.map(this::toResponse)
			.toList();
	}
}
