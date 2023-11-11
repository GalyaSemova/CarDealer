package com.example.mobilele.service.impl;

import com.example.mobilele.model.entity.UserActivationCodeEntity;
import com.example.mobilele.model.events.UserRegisterEvent;
import com.example.mobilele.repository.UserActivationCodeRepository;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.service.EmailService;
import com.example.mobilele.service.UserActivationService;
import com.example.mobilele.service.exception.ObjectNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private static final String ACTIVATION_CODE_SYMBOLS = "abgdfhtjlpsmrtgDGHIOUGHFGDFRSFDPOIEQWFAGHhkjb0877655";
    private static final int ACTIVATION_CODE_LENGTH = 20;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserActivationCodeRepository userActivationCodeRepository;


    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, UserActivationCodeRepository userActivationCodeRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void userRegistered(UserRegisterEvent event) {
        String activationCode = createActivationCode(event.getUserEmail());
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUserNames(),
                activationCode);
    }

    @Override
    public void cleanUpObsoleteActivationLinks() {
//        TODO: implement
//        System.out.println("Not Yet implemented");
    }

    @Override
    public String createActivationCode(String userEmail) {

        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ObjectNotFoundException("User not found")));

        userActivationCodeRepository.save(userActivationCodeEntity);

        return activationCode;
    }

    private static String generateActivationCode() {
        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
        }

        return activationCode.toString();

    }

}
