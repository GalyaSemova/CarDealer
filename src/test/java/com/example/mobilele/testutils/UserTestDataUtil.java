package com.example.mobilele.testutils;

import com.example.mobilele.model.entity.UserEntity;
import com.example.mobilele.model.enums.UserRoleEnum;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;


    public UserEntity createTestUser(String email) {
        return createUser(email, List.of(UserRoleEnum.USER));

    }
    public UserEntity createTestAdmin(String email) {
        return createUser(email, List.of(UserRoleEnum.ADMIN));

    }

    private UserEntity createUser(String email, List<UserRoleEnum> roles) {

        var roleEntities = userRoleRepository.findAllByRoleIn(roles);
        UserEntity newUser = new UserEntity();
        newUser.setActive(true);
        newUser.setEmail(email);
        newUser.setFirstName("Test user first");
        newUser.setLastName("Test user Last");
        newUser.setRoles(roleEntities);

        return userRepository.save(newUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }

}
