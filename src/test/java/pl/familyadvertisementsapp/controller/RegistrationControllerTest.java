package pl.familyadvertisementsapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.familyadvertisementsapp.model.CustomUser;
import pl.familyadvertisementsapp.repository.CustomUserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Integration tests of the registration controller.
 * Covers the full path - from sending request to checking changes in the persistence layer state.
 *
 * @author Tomasz Lipka
 */
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    private final String USERNAME_PARAM = "username";
    private final String PASSWORD_PARAM = "password";
    private final String MOCK_USER = "mock_user";
    private final String ROLE = "USER";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CustomUserRepository customUserRepository;

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void shouldCreateNewRecordInPersistenceLayer() throws Exception {
        mockMvc.perform(post("/registration")
                .param(USERNAME_PARAM, MOCK_USER)
                .param(PASSWORD_PARAM, "123abc!@#")
        );
        CustomUser customUser = customUserRepository.findByUsername(MOCK_USER);
        Assertions.assertNotNull(customUser);
        customUserRepository.deleteById(customUser.getUsername());
    }

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void passwordShouldBeEncodedInPersistenceLayer() throws Exception {
        String password = "123abc!@#";
        mockMvc.perform(post("/registration")
                .param(USERNAME_PARAM, MOCK_USER)
                .param(PASSWORD_PARAM, password)
        );
        CustomUser customUser = customUserRepository.findByUsername(MOCK_USER);
        Assertions.assertNotEquals(password, customUser.getPassword());
        customUserRepository.deleteById(customUser.getUsername());
    }
}