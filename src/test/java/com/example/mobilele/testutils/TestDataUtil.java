package com.example.mobilele.testutils;

import com.example.mobilele.model.entity.OfferEntity;
import com.example.mobilele.model.entity.UserEntity;
import com.example.mobilele.model.enums.UserRoleEnum;
import com.example.mobilele.repository.OfferRepository;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDataUtil {
    @Autowired
    private OfferRepository offerRepository;
//
//    TODO
//    public OfferEntity createTestOffer(UserEntity owner) {
//
//    }
}
