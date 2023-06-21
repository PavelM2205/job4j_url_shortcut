package ru.job4j.url.shortcut.model;

import lombok.Data;

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
