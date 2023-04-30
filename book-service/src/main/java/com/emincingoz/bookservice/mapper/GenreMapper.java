package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.GenreCreateDTO;
import com.emincingoz.bookservice.dto.GenreDTO;
import com.emincingoz.bookservice.repository.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Genre mapper between dto and genre entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre map2Genre(GenreDTO genreDTO);
    Genre map2Genre(GenreCreateDTO genreCreateDTO);
    List<Genre> map2GenreList(List<GenreDTO> genreDTOList);
    GenreDTO map2GenreDTO(Genre genre);
    GenreDTO map2GenreDTO(GenreCreateDTO genreCreateDTO);
    List<GenreDTO> map2GenreDTOList(List<Genre> genreList);
}
