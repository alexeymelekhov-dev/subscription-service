package com.alexeymelekhov.subscriptionservice.subscriptionservice.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    SUBSCRIPTION_NOT_FOUND("Subscription not found with id: %s"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return message.formatted(args);
    }
}
