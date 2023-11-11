package com.example.mobilele.service.impl;

import com.example.mobilele.model.events.UserRegisterEvent;
import com.example.mobilele.service.EmailService;
import com.example.mobilele.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private final EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void userRegistered(UserRegisterEvent event) {
//        TODO : ADD Activation links
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUserNames());
    }

    @Override
    public void cleanUpObsoleteActivationLinks() {
//        TODO: implement
        System.out.println("Not Yet implemented");
    }
}
