package com.example.mobilele.service.impl;

import com.example.mobilele.model.dto.UserLoginDTO;
import com.example.mobilele.model.dto.UserRegistrationDTO;
import com.example.mobilele.model.entity.UserEntity;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.service.UserService;
import com.example.mobilele.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {

        var userEntity = userRepository
                .findByEmail(userLoginDTO.email())
                .orElse(null);

        boolean loginSuccess = false;
        String encodedPassword = userEntity.getPassword();

        if(userEntity != null) {
            String rawPassword = userLoginDTO.password();

           loginSuccess =  (encodedPassword != null) &&
                   passwordEncoder.matches(rawPassword, encodedPassword);

           if(loginSuccess) {
               currentUser.setLogged(true);
               currentUser.setFirstName(userEntity.getFirstName());
               currentUser.setLastName(userEntity.getLastName());
           } else {
               currentUser.logout();
           }
        }


        return loginSuccess;


//        return (encodedPassword != null) ?
//                passwordEncoder.matches(rawPassword, encodedPassword) :
//                false;

//        if (encodedPassword != null) {
//            return passwordEncoder.matches(rawPassword, encodedPassword);
//        } else {
//            return false;
//        }
    }

    @Override
    public void logoutUser() {
        currentUser.logout();
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setActive(true);
        userEntity.setFirstName(userRegistrationDTO.firstName());
        userEntity.setLastName(userRegistrationDTO.lastName());
        userEntity.setEmail(userRegistrationDTO.email());
        userEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));

        return userEntity;

    }
}
