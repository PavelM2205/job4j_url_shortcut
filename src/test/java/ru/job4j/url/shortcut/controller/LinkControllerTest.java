package ru.job4j.url.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.repository.LinkRepository;
import ru.job4j.url.shortcut.service.LinkService;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class LinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private LinkService linkService;

    @AfterEach
    public void clearTables() {
        linkRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void shouldReturnShortLink() throws Exception {
        String url = "http://www.google.com";
        mockMvc.perform(post("/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new HashMap<>() {{
                            put("url", url);
                        }})))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").isNotEmpty());
        Link linkFromDB = linkRepository.findByLongName(url).get();
        assertThat(linkFromDB.getShortName()).isNotEmpty();
    }

    @Test
    public void shouldRedirect() throws Exception {
        String url = "http://www.google.com";
        Link link = new Link();
        link.setLongName(url);
        String shortLink = linkService.generateShortLink(url);
        link.setShortName(shortLink);
        linkRepository.save(link);
        mockMvc.perform(get("/redirect/" + shortLink))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", url));
    }

    @Test
    @WithMockUser
    public void shouldReturnsStatistic() throws Exception {
        String url = "http://www.google.com";
        Link link = new Link();
        link.setLongName(url);
        String shortLink = linkService.generateShortLink(url);
        link.setShortName(shortLink);
        linkRepository.save(link);
        mockMvc.perform(get("/redirect/" + shortLink));
        mockMvc.perform(get("/statistic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].url").value(url))
                .andExpect(jsonPath("$[0].total").value(1));
    }
}