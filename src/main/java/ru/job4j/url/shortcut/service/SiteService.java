package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.repository.SiteRepository;
import ru.job4j.url.shortcut.util.RandomGenerator;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SiteService implements UserDetailsService {
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
            log.error("Exception while creating Site");
        }
        return result;
    }

    public Site findByName(String name) {
        return siteRepository.findByName(name).orElseThrow(() ->
                new NoSuchElementException("Site with such name doesn't exist"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Site> optSite = siteRepository.findByLogin(username);
        if (optSite.isEmpty()) {
            throw new UsernameNotFoundException(String.format(
                    "Site with login %s wasn't found", username));
        }
        Site site = optSite.get();
        return new User(site.getLogin(), site.getPassword(), Collections.emptyList());
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

    public Site generateLoginAndPassword(Site site) {
        site.setLogin(generateRandomLogin());
        site.setPassword(generateRandomPassword());
        return site;
    }
}
