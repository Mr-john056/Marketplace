package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.service.AdService;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdController.class)
class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;

    @Test
    @WithMockUser(username = "testUser", authorities = {"USER"})
    void getAllAds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ads")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", authorities = {"USER"})
    void getAdsMe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", authorities = {"USER"})
    void getAd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", authorities = {"USER"})
    void getImage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/1/image")
                        .accept(MediaType.IMAGE_JPEG))
                .andExpect(status().isOk());
    }
}