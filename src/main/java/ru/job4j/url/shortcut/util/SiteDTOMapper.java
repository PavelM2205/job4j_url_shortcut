package ru.job4j.url.shortcut.util;

import ru.job4j.url.shortcut.dto.SiteDTO;
import ru.job4j.url.shortcut.dto.SiteNameDTO;
import ru.job4j.url.shortcut.model.Site;

public class SiteDTOMapper {

    public static Site fromSiteNameDtoToSite(SiteNameDTO siteNameDTO) {
        Site site = new Site();
        site.setName(siteNameDTO.getSite());
        return site;
    }

    public static SiteDTO fromSiteToSiteDto(Site site) {
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setLogin(site.getLogin());
        siteDTO.setPassword(site.getPassword());
        return siteDTO;
    }

    public static SiteDTO fromSiteToSiteDto(Site site, boolean registration) {
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setLogin(site.getLogin());
        siteDTO.setPassword(site.getPassword());
        siteDTO.setRegistration(registration);
        return siteDTO;
    }
}
