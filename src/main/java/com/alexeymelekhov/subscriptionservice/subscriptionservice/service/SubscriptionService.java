package com.alexeymelekhov.subscriptionservice.subscriptionservice.service;

import com.alexeymelekhov.subscriptionservice.subscriptionservice.dto.SubscriptionDTO;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.exception.ErrorMessage;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.exception.ResourceNotFoundException;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.model.Subscription;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.model.SubscriptionType;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public static final String SUBSCRIPTION_UPDATED = "subscription-updated";

    @Transactional
    public void downgradeExpiredSubscriptions() {
        List<Subscription> expiredSubscriptions = subscriptionRepository
                .findAllByTypeAndExpiresAtBefore(SubscriptionType.PAID, LocalDateTime.now());

        for (Subscription subscription : expiredSubscriptions) {
            subscription.setExpiresAt(null);
            subscription.setType(SubscriptionType.FREE);

            subscriptionRepository.save(subscription);

            kafkaTemplate.send(
                    SUBSCRIPTION_UPDATED,
                    subscription.getLogin()
            );
        }

        log.info("Downgraded expired subscriptions: {}", expiredSubscriptions.size());
    }

    public SubscriptionDTO getSubscription(String login) {

        Subscription subscription = subscriptionRepository.findByLogin(login)
                .orElseThrow(() ->
                    new ResourceNotFoundException(ErrorMessage.SUBSCRIPTION_NOT_FOUND.format(login))
                );

        return new SubscriptionDTO(subscription.getType());
    }
}
