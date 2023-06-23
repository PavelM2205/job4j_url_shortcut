package ru.job4j.url.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.model.Link;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, Integer> {

    Optional<Link> findByLongName(String longName);

    Optional<Link> findByShortName(String shortName);

    @Modifying
    @Query("UPDATE Link SET total = total + 1 WHERE shortName = ?1")
    void incrementTotal(String shortName);

    List<Link> findAll();
}
