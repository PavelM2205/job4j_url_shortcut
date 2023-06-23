package ru.job4j.url.shortcut.dto;

import lombok.Data;
import ru.job4j.url.shortcut.model.Link;

@Data
public class LinkDTO {
    private String code;

    public LinkDTO() {
    }

    public LinkDTO(Link link) {
        this.code = link.getShortName();
    }
}
