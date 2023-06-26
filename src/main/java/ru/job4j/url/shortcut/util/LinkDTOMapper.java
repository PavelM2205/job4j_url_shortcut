package ru.job4j.url.shortcut.util;

import ru.job4j.url.shortcut.dto.LinkStatisticDTO;
import ru.job4j.url.shortcut.dto.LongLinkDTO;
import ru.job4j.url.shortcut.dto.ShortLinkDTO;
import ru.job4j.url.shortcut.model.Link;

public class LinkDTOMapper {

    public static Link fromLongLinkDtoToLink(LongLinkDTO longLinkDTO) {
        Link link = new Link();
        link.setLongName(longLinkDTO.getUrl());
        return link;
    }

    public static Link fromShortLinkDtoToLink(ShortLinkDTO shortLinkDTO) {
        Link link = new Link();
        link.setShortName(shortLinkDTO.getCode());
        return link;
    }

    public static LongLinkDTO fromLinkToLongLinkDto(Link link) {
        LongLinkDTO longLinkDTO = new LongLinkDTO();
        longLinkDTO.setUrl(link.getLongName());
        return longLinkDTO;
    }

    public static ShortLinkDTO fromLinkToShortLinkDto(Link link) {
        ShortLinkDTO shortLinkDTO = new ShortLinkDTO();
        shortLinkDTO.setCode(link.getShortName());
        return shortLinkDTO;
    }

    public static LinkStatisticDTO fromLinkToLinkStatisticDto(Link link) {
        LinkStatisticDTO linkStatisticDTO = new LinkStatisticDTO();
        linkStatisticDTO.setUrl(link.getLongName());
        linkStatisticDTO.setTotal(link.getTotal());
        return linkStatisticDTO;
    }
}
