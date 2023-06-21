package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.repository.SiteRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteService {
    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGITS = "1234567890";
    private static final String SPECIAL_CHARS = "@#&$";
    private static final String LOGIN_SEQUENCE = CHAR_UPPERCASE + CHAR_LOWERCASE + DIGITS;
    private static final String PASSWORD_SEQUENCE = CHAR_UPPERCASE + CHAR_LOWERCASE
            + SPECIAL_CHARS + DIGITS;
    private static final String REFERENCE_SEQUENCE = CHAR_UPPERCASE + CHAR_LOWERCASE
            + DIGITS;
    private static final int PASSWORD_LENGTH = 6;
    private static final int LOGIN_LENGTH = 6;
    private static final int REFERENCE_LENGTH = 7;
    private final SecureRandom random = new SecureRandom();
    private final SiteRepository siteRepository;


    private String generateRandomString(String input, int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(input.length());
            result.append(input.charAt(randomIndex));
        }
        return result.toString();
    }

    private String generateRandomLogin() {
        return generateRandomString(LOGIN_SEQUENCE, LOGIN_LENGTH);
    }

    private String generateRandomPassword() {
        return generateRandomString(PASSWORD_SEQUENCE, PASSWORD_LENGTH);
    }

    private String generateRandomReference() {
        return generateRandomString(REFERENCE_SEQUENCE, REFERENCE_LENGTH);
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
