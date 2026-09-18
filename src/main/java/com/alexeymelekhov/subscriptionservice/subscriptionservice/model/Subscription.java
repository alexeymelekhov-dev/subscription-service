package com.alexeymelekhov.subscriptionservice.subscriptionservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "subscriptions")
public class Subscription {

    @Id
    private String login;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionType type;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}
