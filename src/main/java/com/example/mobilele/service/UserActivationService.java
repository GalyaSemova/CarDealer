package com.example.mobilele.service;

import com.example.mobilele.model.events.UserRegisterEvent;

public interface UserActivationService {

    void userRegistered(UserRegisterEvent event);
}
