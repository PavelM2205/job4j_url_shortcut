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
    @Column(name = "long_name", unique = true)
    private String longName;
    @Column(name = "short_name", unique = true)
    private String shortName;
    private int total;
}
