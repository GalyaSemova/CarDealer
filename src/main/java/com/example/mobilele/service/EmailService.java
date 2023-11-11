package com.example.mobilele.service;

public interface EmailService {

    void sendRegistrationEmail(
            String userEmail,
            String userName,
            String activationCode);
}
