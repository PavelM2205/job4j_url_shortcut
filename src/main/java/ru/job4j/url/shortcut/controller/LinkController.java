package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.LinkDTO;
import ru.job4j.url.shortcut.service.LinkService;

import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/convert")
    public LinkDTO convert(@RequestBody Map<String, String> body) {
        String longName = body.get("url");
        Link link = new Link();
        link.setLongName(longName);
        link.setShortName(linkService.generateShortLink(longName));
        Optional<Link> optLink = linkService.save(link);
        if (optLink.isEmpty()) {
            return new LinkDTO(linkService.findByLongName(longName));
        }
        return new LinkDTO(optLink.get());
    }

    @GetMapping("/redirect/{shortLink}")
    public ResponseEntity<Void> shortLinkRedirect(@PathVariable String shortLink) {
        System.out.println(shortLink);
        Link link = linkService.findByShortName(shortLink);
        linkService.incrementTotal(shortLink);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, link.getLongName())
                .build();
    }
}
