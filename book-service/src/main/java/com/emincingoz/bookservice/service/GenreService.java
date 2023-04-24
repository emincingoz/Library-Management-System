package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.GenreCreateDTO;
import com.emincingoz.bookservice.dto.GenreDTO;

import java.util.Collection;
import java.util.List;

public interface GenreService {

    GenreDTO getGenreByGenreName(String genreName);
    List<GenreDTO> getGenresByGenreNameList(Collection<String> genreNameList);

    GenreDTO addGenre(GenreCreateDTO genreCreateDTO);
}
