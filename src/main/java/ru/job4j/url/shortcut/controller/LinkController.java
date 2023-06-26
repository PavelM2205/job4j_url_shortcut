package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.shortcut.dto.LongLinkDTO;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.dto.ShortLinkDTO;
import ru.job4j.url.shortcut.dto.LinkStatisticDTO;
import ru.job4j.url.shortcut.service.LinkService;
import ru.job4j.url.shortcut.util.LinkDTOMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.job4j.url.shortcut.util.LinkDTOMapper.*;

@RestController
@AllArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/convert")
    public ResponseEntity<ShortLinkDTO> convert(@Valid @RequestBody LongLinkDTO longLinkDTO) {
        Link link = fromLongLinkDtoToLink(longLinkDTO);
        link.setShortName(linkService.generateShortLink(link.getLongName()));
        Optional<Link> optLink = linkService.save(link);
        if (optLink.isEmpty()) {
            return ResponseEntity.ok(fromLinkToShortLinkDto(linkService.findByLongName(link.getLongName())));
        }
        return ResponseEntity.ok(fromLinkToShortLinkDto(optLink.get()));
    }

    @GetMapping("/redirect/{shortLink}")
    public ResponseEntity<Void> shortLinkRedirect(@PathVariable String shortLink) {
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
                .map(LinkDTOMapper::fromLinkToLinkStatisticDto)
                .collect(Collectors.toList()));
    }
}
