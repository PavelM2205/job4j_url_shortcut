package ru.job4j.url.shortcut.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SiteNameDTO {
    @NotBlank(message = "Site mustn't be empty")
    private String site;
}
