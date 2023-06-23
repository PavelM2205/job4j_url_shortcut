package ru.job4j.url.shortcut.dto;

import lombok.Data;
import ru.job4j.url.shortcut.model.Site;

@Data
public class SiteDTO {
    private boolean registration;
    private String login;
    private String password;

    public SiteDTO() {
    }

    public SiteDTO(Site site, boolean registrationResult) {
        this.login = site.getLogin();
        this.password = site.getPassword();
        this.registration = registrationResult;
    }
}
