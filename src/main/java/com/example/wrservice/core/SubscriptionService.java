package com.example.wrservice.core;

import com.example.wrservice.core.utils.dto.SubscriptionRequest;
import com.example.wrservice.core.utils.dto.SubscriptionResponse;
import com.example.wrservice.core.utils.dto.SubscriptionStat;
import com.example.wrservice.core.utils.exception.SubscriptionAlreadyExistsException;
import com.example.wrservice.core.utils.dto.DTOMapper;
import com.example.wrservice.data.model.Subscription;
import com.example.wrservice.data.model.User;
import com.example.wrservice.data.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionService {

	private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

	private final SubscriptionRepository subscriptionRepository;
	private final UserService userService;
	private final DTOMapper mapper;

	@Autowired
	public SubscriptionService(
		SubscriptionRepository subscriptionRepository,
		UserService userService,
		DTOMapper mapper
	) {
		this.subscriptionRepository = subscriptionRepository;
		this.userService = userService;
		this.mapper = mapper;
	}

	@Transactional
	public SubscriptionResponse add(long userId, SubscriptionRequest subscriptionRequest) {
		User user = userService.findUserOrThrowException(userId);

		// validate user has this subscription
		List<String> subs = mapper.subscriptionsToNameList(user.getSubscriptions());
		if (subs.contains(subscriptionRequest.serviceName())) {
			throw new SubscriptionAlreadyExistsException(
				"Подписка на сервис %s у пользователя %d уже есть".formatted(
					subscriptionRequest.serviceName(),
					userId
				)
			);
		}

		// dto -> entity
		Subscription subscription = mapper.toEntity(subscriptionRequest);

		// add sub to user & save subscription
		subscription.setUser(user);
		subscriptionRepository.save(subscription);
		log.info("Подписка {} добавлена", subscription.getServiceName());

		// return dto
		return mapper.toResponse(subscription);
	}

	@Transactional
	public List<SubscriptionResponse> getSubs(long userId) {
		User user = userService.findUserOrThrowException(userId);
		return mapper.subscriptionsToResponseList(user.getSubscriptions());
	}

	public void delete(long subId) {
		subscriptionRepository.deleteById(subId);
	}

	public List<SubscriptionStat> findTopSubscriptions() {
		return subscriptionRepository.findTopSubscriptions();
	}
}
