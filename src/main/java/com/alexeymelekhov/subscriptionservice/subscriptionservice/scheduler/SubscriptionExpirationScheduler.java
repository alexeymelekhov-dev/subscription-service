package com.alexeymelekhov.subscriptionservice.subscriptionservice.scheduler;

import com.alexeymelekhov.subscriptionservice.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionExpirationScheduler {

    private final SubscriptionService subscriptionService;

    @Scheduled(fixedRate = 60_000)
    @SchedulerLock(
            name = "downgradeExpiredSubscriptions",
            lockAtMostFor = "50s",
            lockAtLeastFor = "10s"
    )
    public void downgradeExpiredSubscriptions() {
        subscriptionService.downgradeExpiredSubscriptions();
    }
}
