package com.example.mobilele.service;

import com.example.mobilele.model.events.UserRegisterEvent;

public interface UserActivationService {

    void userRegistered(UserRegisterEvent event);

    void cleanUpObsoleteActivationLinks();

    String createActivationCode(String userEmail);

}
