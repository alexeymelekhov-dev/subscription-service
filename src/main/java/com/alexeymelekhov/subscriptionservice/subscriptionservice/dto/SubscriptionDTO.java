package com.alexeymelekhov.subscriptionservice.subscriptionservice.dto;


import com.alexeymelekhov.subscriptionservice.subscriptionservice.model.SubscriptionType;

public record SubscriptionDTO(
        SubscriptionType type
) {
}
