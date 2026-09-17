package com.alexeymelekhov.subscriptionservice.subscriptionservice.controller.api.v1;

import com.alexeymelekhov.subscriptionservice.subscriptionservice.dto.SubscriptionDTO;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/{login}")
    public SubscriptionDTO getSubscription(@PathVariable String login) {
        return subscriptionService.getSubscription(login);
    }
}
