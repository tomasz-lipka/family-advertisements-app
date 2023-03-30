package pl.familyadvertisementsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.familyadvertisementsapp.model.Advertisement;
import pl.familyadvertisementsapp.repository.AdvertisementRepository;

@SpringBootTest
@AutoConfigureMockMvc
class AdvertisementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    void delete() throws Exception {
//        mockMvc.perform(delete("/advertisements/{id}", 5352L));
//        Optional<Advertisement> byId = advertisementRepository.findById(5352L);
//        assertTrue(byId.isEmpty());
//    }

    @Test
    @WithMockUser(username = "user4", roles = "USER")
    void create() throws Exception {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle("title");
        advertisement.setDescription("desc desc desc desc desc desc");
        advertisement.setOwnerUsername("owner");

        String content = "{\"title\":\"MOCKMVC4\",\"description\":\"postmpostmpostmpostmpostmpostmpostmpostmpostmpostmpostmpostm\",\"created\":\"2023-03-28T13:27:26.536+00:00\",\"ownerUsername\":\"qq\"}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/advertisements/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());

    }
}