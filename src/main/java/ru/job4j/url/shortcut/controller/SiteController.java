package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.shortcut.dto.SiteNameDTO;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.dto.SiteDTO;
import ru.job4j.url.shortcut.service.SiteService;

import javax.validation.Valid;
import java.util.Optional;

import static ru.job4j.url.shortcut.util.SiteDTOMapper.*;

@RestController
@AllArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping("/registration")
    public ResponseEntity<SiteDTO> registration(@Valid @RequestBody SiteNameDTO siteNameDTO) {
        Site site = fromSiteNameDtoToSite(siteNameDTO);
        siteService.generateLoginAndPassword(site);
        Optional<Site> optSite = siteService.create(site);
        if (optSite.isEmpty()) {
            return ResponseEntity.ok(fromSiteToSiteDto(siteService.findByName(site.getName())));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fromSiteToSiteDto(site, true));
    }
}
