package com.alexeymelekhov.subscriptionservice.subscriptionservice.repository;

import com.alexeymelekhov.subscriptionservice.subscriptionservice.model.Subscription;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.model.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    Optional<Subscription> findByLogin(String login);

    List<Subscription> findAllByTypeAndExpiresAtBefore(SubscriptionType type, LocalDateTime now);
}
