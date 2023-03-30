package pl.familyadvertisementsapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdvertisementControllerTest {

    private final String TITLE_PARAM = "title";
    private final String DESCRIPTION_PARAM = "description";
    private final String MOCK_USER = "mock_user";
    private final String ROLE = "USER";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void shouldCreateNewAdvertisement() throws Exception {
        mockMvc
                .perform(post("/advertisements")
                        .param(TITLE_PARAM, "MOCK7")
                        .param(DESCRIPTION_PARAM, "asd asd asd asd asd asd asd asd ")
                )
                .andExpect(status().is3xxRedirection());
    }
}