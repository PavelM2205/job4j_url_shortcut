package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.repository.LinkRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
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

    public String generateShortLink(String longName) {
        return randomGenerator.generateRandomExpression(REFERENCES_LENGTH);
    }

    public Optional<Link> save(Link link) {
        Optional<Link> result = Optional.empty();
        try {
            linkRepository.save(link);
            result = Optional.of(link);
        } catch (DataIntegrityViolationException exc) {
            return result;
        }
        return result;
    }

    @Transactional
    public void incrementTotal(String shortName) {
        linkRepository.incrementTotal(shortName);
    }
}
