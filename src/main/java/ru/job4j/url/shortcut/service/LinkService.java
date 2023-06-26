package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.repository.LinkRepository;
import ru.job4j.url.shortcut.util.RandomGenerator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LinkService {
    private static final int REFERENCES_LENGTH = 7;

    private final RandomGenerator randomGenerator;
    private final LinkRepository linkRepository;

    public Link findByLongName(String longName) {
        return linkRepository.findByLongName(longName).orElseThrow(() ->
                new NoSuchElementException("Link with such longName doesn't exist"));
    }

    public Link findByShortName(String shortName) {
        return linkRepository.findByShortName(shortName).orElseThrow(() ->
                new NoSuchElementException("Link with such shortName doesn't exist"));
    }

    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    public String generateShortLink(String longName) {
        return randomGenerator.generateRandomExpression(REFERENCES_LENGTH);
    }

    public Optional<Link> save(Link link) {
        Optional<Link> result = Optional.empty();
        try {
            linkRepository.save(link);
            result = Optional.of(link);
        } catch (DataIntegrityViolationException exc) {
            log.error("Exception while saving Link");
        }
        return result;
    }

    @Transactional
    public void incrementTotal(String shortName) {
        linkRepository.incrementTotal(shortName);
    }
}
