package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.model.Site;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {

    Optional<Site> findByName(String name);

    List<Site> findAll();
}
