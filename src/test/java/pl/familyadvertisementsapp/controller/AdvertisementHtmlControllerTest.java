package pl.familyadvertisementsapp.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests of the advertisement controller.
 * Covers the full path - from sending request to checking changes in the persistence layer state.
 *
 * @author Tomasz Lipka
 */
@SpringBootTest
@AutoConfigureMockMvc
class AdvertisementHtmlControllerTest {

    private final String TITLE_PARAM = "title";
    private final String DESCRIPTION_PARAM = "description";
    private final String MOCK_USER = "mock_user";
    private final String ROLE = "USER";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void shouldRedirectAfterCreate() throws Exception {
        String title = getRandomString();
        String description = getRandomString();
        mockMvc.perform(post("/advertisements")
                        .param(TITLE_PARAM, title)
                        .param(DESCRIPTION_PARAM, description)
                )
                .andExpect(status().is3xxRedirection());
        Advertisement advertisement = advertisementRepository.findByOwnerUsername(MOCK_USER)
                .stream()
                .filter(n -> n.getTitle().equals(title))
                .filter(n -> n.getDescription().equals(description))
                .findFirst()
                .get();
        advertisementRepository.deleteById(advertisement.getId());
    }

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void shouldCreateNewRecordInPersistenceLayer() throws Exception {
        String title = getRandomString();
        String description = getRandomString();
        mockMvc.perform(post("/advertisements")
                .param(TITLE_PARAM, title)
                .param(DESCRIPTION_PARAM, description)
        );
        Advertisement advertisement = advertisementRepository.findByOwnerUsername(MOCK_USER)
                .stream()
                .filter(n -> n.getTitle().equals(title))
                .filter(n -> n.getDescription().equals(description))
                .findFirst()
                .get();
        Assertions.assertNotNull(advertisement);
        advertisementRepository.deleteById(advertisement.getId());
    }

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void shouldReturnSameNumberOfRecordsAsInPersistenceLayer() throws Exception {
        long size = advertisementRepository.findAll().size();
        mockMvc.perform(
                        get("/advertisements/all")
                )
                .andExpect(
                        model().attribute("advertisements", hasSize((int) size))
                );
    }

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void shouldDeleteRecordFromPersistenceLayer() throws Exception {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(getRandomString());
        advertisement.setDescription(getRandomString());
        advertisement.setCreated(new Date());
        advertisement.setOwnerUsername(MOCK_USER);
        Advertisement saved = advertisementRepository.save(advertisement);
        mockMvc.perform(delete("/advertisements/" + saved.getId()));
        assertTrue(advertisementRepository.findById(saved.getId()).isEmpty());
    }

    @Test
    @WithMockUser(username = MOCK_USER, roles = ROLE)
    void shouldUpdateTitleInPersistenceLayer() throws Exception {
        var description = getRandomString();
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(getRandomString());
        advertisement.setDescription(description);
        advertisement.setCreated(new Date());
        advertisement.setOwnerUsername(MOCK_USER);

        Advertisement saved = advertisementRepository.save(advertisement);
        String newTitle = getRandomString();
        mockMvc.perform(put("/advertisements")
                .param(TITLE_PARAM, newTitle)
                .param(DESCRIPTION_PARAM, description)
                .param("ownerUsername", MOCK_USER)
                .param("id", String.valueOf(saved.getId()))
        );
        boolean isNewTitle = advertisementRepository.findById(saved.getId()).get().getTitle().equals(newTitle);
        assertTrue(isNewTitle);

        advertisementRepository.deleteById(saved.getId());
    }

    private String getRandomString() {
        return RandomStringUtils.randomAlphabetic(20);
    }
}