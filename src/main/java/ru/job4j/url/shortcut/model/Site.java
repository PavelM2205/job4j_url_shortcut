package ru.job4j.url.shortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sites")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @Column(unique = true)
    private String name;
    private String login;
    private String password;
 }
