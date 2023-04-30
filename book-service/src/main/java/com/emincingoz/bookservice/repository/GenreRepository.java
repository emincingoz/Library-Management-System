package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for GENRE entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {

    /**
     * Finds Genre by given genre name
     * @param genreName
     * @return Optional<Genre>
     */
    Optional<Genre> findByGenre(String genreName);

    /**
     * Finds genre list by given genre list
     * @param genreNameList
     * @return List<Genre>
     */
    List<Genre> findGenresByGenreIn(Collection<String> genreNameList);
}