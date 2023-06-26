package ru.job4j.url.shortcut.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LongLinkDTO {
    @NotBlank(message = "URL mustn't be empty")
    private String url;
}
