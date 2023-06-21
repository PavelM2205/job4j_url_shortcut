package ru.job4j.url.shortcut.model;

import lombok.Data;

@Data
public class LinkStatisticDTO {
    private String url;
    private int total;

    public LinkStatisticDTO() {
    }

    public LinkStatisticDTO(Link link) {
        this.url = link.getLongName();
        this.total = link.getTotal();
    }
}
