package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenre(String genreName);

    List<Genre> findGenresByGenreIn(Collection<String> genreNameList);
}