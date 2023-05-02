package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.GenreCreateDTO;
import com.emincingoz.bookservice.dto.GenreDTO;
import com.emincingoz.bookservice.repository.entity.Genre;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T10:45:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre map2Genre(GenreDTO genreDTO) {
        if ( genreDTO == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setId( genreDTO.getId() );
        genre.setGenre( genreDTO.getGenre() );

        return genre;
    }

    @Override
    public Genre map2Genre(GenreCreateDTO genreCreateDTO) {
        if ( genreCreateDTO == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setGenre( genreCreateDTO.getGenre() );

        return genre;
    }

    @Override
    public List<Genre> map2GenreList(List<GenreDTO> genreDTOList) {
        if ( genreDTOList == null ) {
            return null;
        }

        List<Genre> list = new ArrayList<Genre>( genreDTOList.size() );
        for ( GenreDTO genreDTO : genreDTOList ) {
            list.add( map2Genre( genreDTO ) );
        }

        return list;
    }

    @Override
    public GenreDTO map2GenreDTO(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDTO.GenreDTOBuilder genreDTO = GenreDTO.builder();

        genreDTO.id( genre.getId() );
        genreDTO.genre( genre.getGenre() );

        return genreDTO.build();
    }

    @Override
    public GenreDTO map2GenreDTO(GenreCreateDTO genreCreateDTO) {
        if ( genreCreateDTO == null ) {
            return null;
        }

        GenreDTO.GenreDTOBuilder genreDTO = GenreDTO.builder();

        genreDTO.genre( genreCreateDTO.getGenre() );

        return genreDTO.build();
    }

    @Override
    public List<GenreDTO> map2GenreDTOList(List<Genre> genreList) {
        if ( genreList == null ) {
            return null;
        }

        List<GenreDTO> list = new ArrayList<GenreDTO>( genreList.size() );
        for ( Genre genre : genreList ) {
            list.add( map2GenreDTO( genre ) );
        }

        return list;
    }
}
