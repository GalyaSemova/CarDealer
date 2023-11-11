package com.example.mobilele.service.schedulers;

import com.example.mobilele.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivationLinkCleanupScheduler {

    private final UserActivationService userActivationService;


    public ActivationLinkCleanupScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(cron = "*/10 * * * * *")
//    @Scheduled(fixedRate = 10_000,
//    initialDelay = 10_000)
    public void cleanUp() {
        System.out.println("Trigger cleaned of activation links. " + LocalDateTime.now());

    }
}
