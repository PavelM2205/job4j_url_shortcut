package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.repository.LinkRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class LinkServiceTest {

    @Autowired
    private LinkService linkService;

    @Autowired
    private LinkRepository linkRepository;

    @AfterEach
    public void  clearTables() {
        linkRepository.deleteAll();
    }

    @Test
    public void whenIncrementTotalThenTotalMustBeOne() {
        Link link = new Link();
        link.setLongName("long");
        link.setShortName("short");
        linkService.save(link);
        linkService.incrementTotal(link.getShortName());
        Link linkFromDB = linkService.findByLongName(link.getLongName());
        assertThat(linkFromDB.getTotal()).isEqualTo(1);
    }

    @Test
    public void whenSaveLinkWithSameLongNameThenReturnsEmptyOptional() {
        Link link1 = new Link();
        String longName = "long";
        link1.setLongName(longName);
        link1.setShortName("short");
        Link link2 = new Link();
        link2.setLongName(longName);
        link2.setShortName("anotherShortName");
        linkService.save(link1);
        Optional<Link> optLink = linkService.save(link2);
        assertThat(optLink.isEmpty()).isTrue();
    }

    @Test
    public void whenSaveLinkWithSameShortNameThenReturnsEmptyOptional() {
        Link link1 = new Link();
        String shortName = "short";
        link1.setLongName("long");
        link1.setShortName(shortName);
        Link link2 = new Link();
        link2.setLongName("anotherLongName");
        link2.setShortName(shortName);
        linkService.save(link1);
        Optional<Link> optLink = linkService.save(link2);
        assertThat(optLink.isEmpty()).isTrue();
    }

    @Test
    public void whenSaveLinkThenReturnsOptional() {
        Link link = new Link();
        link.setLongName("long");
        link.setShortName("short");
        Optional<Link> optLink = linkService.save(link);
        assertThat(optLink.isPresent()).isTrue();
    }
}