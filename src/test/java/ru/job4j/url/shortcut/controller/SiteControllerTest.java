package ru.job4j.url.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.repository.SiteRepository;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SiteControllerTest {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void cleanTables() {
        siteRepository.deleteAll();
    }

    @Test
    void shouldReturnsTrueAndLoginAndPassword() throws Exception {
        String site = "www.google.com";
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HashMap<>() {{
                    put("site", site);
                }})))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.registration").value(true))
                .andExpect(jsonPath("$.login").isNotEmpty())
                .andExpect(jsonPath("$.password").isNotEmpty());
    }

    @Test
    void shouldReturnsFalseAndLoginAndPassword() throws Exception {
        String siteName = "www.google.com";
        Site site = new Site();
        site.setName(siteName);
        site.setLogin("login");
        site.setPassword("password");
        siteRepository.save(site);
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HashMap<>() {{
                    put("site", siteName);
                }})))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registration").value(false))
                .andExpect(jsonPath("$.login").value(site.getLogin()))
                .andExpect(jsonPath("$.password").value(site.getPassword()));
    }
}