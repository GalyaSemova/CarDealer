package com.example.mobilele.testutils;

import com.example.mobilele.model.entity.UserRoleEntity;
import com.example.mobilele.model.enums.UserRoleEnum;
import com.example.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DBInit implements CommandLineRunner {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public void run(String... args) throws Exception {
        UserRoleEntity roleUser = new UserRoleEntity();
        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleUser.setRole(UserRoleEnum.USER);
        roleAdmin.setRole(UserRoleEnum.ADMIN);


        if(userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                    roleUser,
                    roleAdmin
            ));
        }
    }
}
