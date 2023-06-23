package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.repository.SiteRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SiteServiceTest {

    @Autowired
    private SiteService siteService;

    @Autowired
    private SiteRepository siteRepository;

    @AfterEach
    public void clearTables() {
        siteRepository.deleteAll();
    }

    @Test
    void whenCreateThenReturnsOptional() {
        Site site = new Site();
        site.setName("name");
        site.setLogin("login");
        site.setPassword("password");
        Optional<Site> optSite = siteService.create(site);
        assertThat(optSite.isPresent()).isTrue();
    }

    @Test
    public void whenCreateSiteWithSameNameThenReturnsEmptyOptional() {
        String name = "name";
        Site site1 = new Site();
        site1.setName(name);
        site1.setLogin("login");
        site1.setPassword("password");
        Site site2 = new Site();
        site2.setName(name);
        site2.setLogin("anotherLogin");
        site2.setPassword("anotherPassword");
        siteService.create(site1);
        Optional<Site> optSite = siteService.create(site2);
        assertThat(optSite.isEmpty()).isTrue();
    }
}