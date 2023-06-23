package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.LinkDTO;
import ru.job4j.url.shortcut.model.LinkStatisticDTO;
import ru.job4j.url.shortcut.service.LinkService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/convert")
    public ResponseEntity<LinkDTO> convert(@RequestBody Map<String, String> body) {
        String longName = body.get("url");
        if (longName.isEmpty()) {
            throw new NullPointerException("Parameter url mustn't be empty");
        }
        Link link = new Link();
        link.setLongName(longName);
        link.setShortName(linkService.generateShortLink(longName));
        Optional<Link> optLink = linkService.save(link);
        if (optLink.isEmpty()) {
            return ResponseEntity.ok(new LinkDTO(linkService.findByLongName(longName)));
        }
        return ResponseEntity.ok(new LinkDTO(optLink.get()));
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

    @GetMapping("/statistic")
    public ResponseEntity<List<LinkStatisticDTO>> getStatistic() {
        List<Link> links = linkService.findAll();
        return ResponseEntity.ok(links.stream()
                .map(LinkStatisticDTO::new)
                .collect(Collectors.toList()));
    }
}
