package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.GenreCreateDTO;
import com.emincingoz.bookservice.dto.GenreDTO;

import java.util.Collection;
import java.util.List;

/**
 * Genre service interface for book genre operations
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface GenreService {

    /**
     * Returns the genre by given genre name
     * @param genreName
     * @return GenreDTO
     */
    GenreDTO getGenreByGenreName(String genreName);

    /**
     * Returns the genre list by given genre name list
     * @param genreNameList
     * @return List<GenreDTO>
     */
    List<GenreDTO> getGenresByGenreNameList(Collection<String> genreNameList);

    /**
     * Adds new genre if genre is not already added. Then returns the added genre as dto
     * @param genreCreateDTO
     * @return GenreDTO
     */
    GenreDTO addGenre(GenreCreateDTO genreCreateDTO);
}
