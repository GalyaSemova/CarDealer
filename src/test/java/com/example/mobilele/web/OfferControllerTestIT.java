package com.example.mobilele.web;

import com.example.mobilele.model.entity.OfferEntity;
import com.example.mobilele.model.entity.UserEntity;
import com.example.mobilele.testutils.TestDataUtil;
import com.example.mobilele.testutils.UserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTestIT {

    private static final String TEST_USER_EMAIL = "user@example.com";
    private static final String TEST_USER1_EMAIL = "user1@example.com";
    private static final String TEST_ADMIN_EMAIL = "admin@example.com";

    @Autowired
    private TestDataUtil testDataUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserTestDataUtil userTestDataUtil;

    @BeforeEach
    void setUp() {
        testDataUtil.cleanAllTestData();
        userTestDataUtil.cleanUp();
    }

    @AfterEach
    void tearDown() {
        testDataUtil.cleanAllTestData();
        userTestDataUtil.cleanUp();
    }

    @Test
    void testAnonymousDeletionFails() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser("test@example.com");
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(
                delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));

    }
    @Test
    @WithMockUser(username = TEST_USER_EMAIL,
    roles = {"USER"})
    void testNonAdminUserOwnedOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);
        mockMvc.perform(
                        delete("/offer/{uuid}", offerEntity.getUuid())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));
    }
    @Test
    @WithMockUser(username = TEST_USER1_EMAIL,
            roles = {"USER"})
    void testNonAdminUserNotOwnedOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER_EMAIL);
        UserEntity anotherUser = userTestDataUtil.createTestUser(TEST_USER1_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(
                        delete("/offer/{uuid}", offerEntity.getUuid())
                                .with(csrf())
                )
                .andExpect(status().isForbidden());

    }
    @Test
    @WithMockUser(username = TEST_ADMIN_EMAIL,
            roles = {"USER", "ADMIN"})
    void testAdminUserOwnedOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER_EMAIL);
        userTestDataUtil.createTestAdmin(TEST_ADMIN_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(
                        delete("/offer/{uuid}", offerEntity.getUuid())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));


    }

    void testAdminUserNotOwnedOffer() {

    }
}
