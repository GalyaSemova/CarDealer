package com.example.mobilele.service.impl;

import com.example.mobilele.model.entity.UserEntity;
import com.example.mobilele.model.entity.UserRoleEntity;
import com.example.mobilele.model.enums.UserRoleEnum;
import com.example.mobilele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class MobileleUserDetailsServiceTest {
    @ExtendWith(MockitoExtension.class)
    private MobileleUserDetailsService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new MobileleUserDetailsService(
           mockUserRepository
        );
    }

//    @Test
//    void testMock() {
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setFirstName("Anna");
//
//        when(mockUserRepository.findByEmail("test@example.com"))
//                .thenReturn(Optional.of(userEntity));
//        Optional<UserEntity> userOpt =
//                mockUserRepository.findByEmail("test@example.com");
//        UserEntity user = userOpt.get();
//
//        Assertions.assertEquals("Anna", user.getFirstName());
//    }

    @Test
    void testUserNotFound() {
//        test does not pass
//         when(mockUserRepository.findByEmail("pesho@test.com"))
//                 .thenReturn(Optional.of(new UserEntity()));

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("pesho@test.com")
        );
    }

    @Test
    void testUserFoundException() {
//        arrange
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

//        act
        UserDetails userDetails
                = serviceToTest.loadUserByUsername(testUserEntity.getEmail());

//        assert
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername(),
                "Username is not mapped to email");

        Assertions.assertEquals(testUserEntity.getPassword(),
                userDetails.getPassword());

        Assertions.assertEquals(2,
                userDetails.getAuthorities().size());

        Assertions.assertTrue(containsAuthority(userDetails,
                        "ROLE_" + UserRoleEnum.ADMIN),
                "The user is not admin");
        Assertions.assertTrue(containsAuthority(userDetails,
                        "ROLE_" + UserRoleEnum.USER),
                "The user is not user");

    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));

    }

    private static UserEntity createTestUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("firstName");
        userEntity.setLastName("lastName");
        userEntity.setEmail("test@test.com");
        userEntity.setPassword("topsecret");
        userEntity.setActive(false);
        UserRoleEntity role1 = new UserRoleEntity();
        UserRoleEntity role2 = new UserRoleEntity();
        role1.setRole(UserRoleEnum.ADMIN);
        role2.setRole(UserRoleEnum.USER);
        userEntity.setRoles(List.of(
              role1, role2
        ));

        return userEntity;
    }
}
