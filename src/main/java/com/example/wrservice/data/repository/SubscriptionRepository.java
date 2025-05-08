package com.example.wrservice.data.repository;

import com.example.wrservice.core.utils.dto.SubscriptionStat;
import com.example.wrservice.data.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	@Query(
		value = "SELECT service_name AS name, COUNT(*) AS count " +
			"FROM subscriptions " +
			"GROUP BY service_name " +
			"ORDER BY count DESC " +
			"LIMIT 3",
		nativeQuery = true
	)
	List<SubscriptionStat> findTopSubscriptions();
}
