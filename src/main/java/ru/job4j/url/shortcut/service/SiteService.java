package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.repository.SiteRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteService {
    private static final int PASSWORD_LENGTH = 6;
    private static final int LOGIN_LENGTH = 6;
    private final RandomGenerator randomGenerator;
    private final SiteRepository siteRepository;

    private String generateRandomLogin() {
        return randomGenerator.generateRandomExpression(LOGIN_LENGTH);
    }

    private String generateRandomPassword() {
        return randomGenerator.generateRandomPassword(PASSWORD_LENGTH);
    }

    public Optional<Site> create(Site site) {
        Optional<Site> result = Optional.empty();
        try {
            siteRepository.save(site);
            result = Optional.of(site);
        } catch (DataIntegrityViolationException exc) {
            return result;
        }
        return result;
    }

    public Site findByName(String name) {
        return siteRepository.findByName(name).orElseThrow(() ->
                new NoSuchElementException("Site with such name doesn't exist"));
    }

    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    public Site generateLoginAndPassword(String siteName) {
        Site site = new Site();
        site.setName(siteName);
        site.setLogin(generateRandomLogin());
        site.setPassword(generateRandomPassword());
        return site;
    }
}
