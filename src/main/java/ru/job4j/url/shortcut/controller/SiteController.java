package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.model.SiteDTO;
import ru.job4j.url.shortcut.service.SiteService;

import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping("/registration")
    public SiteDTO registration(@RequestBody Map<String, String> body) {
        String siteName = body.get("site");
        boolean registrationResult = false;
        Site site = siteService.generateLoginAndPassword(siteName);
        Optional<Site> optSite = siteService.create(site);
        if (optSite.isEmpty()) {
            Site siteFromDB = siteService.findByName(siteName);
            return new SiteDTO(siteFromDB, registrationResult);
        }
        registrationResult = true;
        return new SiteDTO(optSite.get(), registrationResult);
    }
}