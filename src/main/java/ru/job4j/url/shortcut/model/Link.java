package ru.job4j.url.shortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "links")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @Column(unique = true)
    private String longName;
    @Column(unique = true)
    private String shortName;
}
