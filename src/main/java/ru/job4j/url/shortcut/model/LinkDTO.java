package ru.job4j.url.shortcut.model;

import lombok.Data;

@Data
public class LinkDTO {
    private String code;

    public LinkDTO() {
    }

    public LinkDTO(Link link) {
        this.code = link.getShortName();
    }
}
