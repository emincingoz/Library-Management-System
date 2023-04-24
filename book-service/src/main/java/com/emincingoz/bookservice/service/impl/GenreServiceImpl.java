package com.emincingoz.bookservice.service.impl;

import com.emincingoz.bookservice.dto.GenreCreateDTO;
import com.emincingoz.bookservice.dto.GenreDTO;
import com.emincingoz.bookservice.exception.GenreException;
import com.emincingoz.bookservice.mapper.GenreMapper;
import com.emincingoz.bookservice.repository.GenreRepository;
import com.emincingoz.bookservice.repository.entity.Genre;
import com.emincingoz.bookservice.service.GenreService;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public GenreDTO getGenreByGenreName(String genreName) {
        if (Strings.isNullOrEmpty(genreName)) {
            log.warn("genreName is null or empty");
            throw GenreException.invalidParameter(GenreException.INVALID_PARAMETER);
        }
        Genre genre = genreRepository.findByGenre(genreName).orElseThrow(() -> {
            log.warn("genre is null or empty");
            throw GenreException.invalidParameter(GenreException.GENRE_NOT_FOUND);
        });
        return genreMapper.map2GenreDTO(genre);
    }

    @Override
    public List<GenreDTO> getGenresByGenreNameList(Collection<String> genreNameList) {
        if (CollectionUtils.isEmpty(genreNameList)) {
            log.warn("genreNameList is null or empty");
            throw GenreException.invalidParameter(GenreException.INVALID_PARAMETER);
        }
        List<Genre> genreList = genreRepository.findGenresByGenreIn(genreNameList);
        if (CollectionUtils.isEmpty(genreList)) {
            log.warn("genreList is null or empty");
            throw GenreException.dataNotFoundException(GenreException.GENRE_NOT_FOUND);
        }
        return genreMapper.map2GenreDTOList(genreList);
    }

    @Override
    public GenreDTO addGenre(GenreCreateDTO genreCreateDTO) {
        if (genreCreateDTO == null) {
            log.warn("genreCreateDTO is null");
            throw GenreException.invalidParameter(GenreException.INVALID_PARAMETER);
        }
        Optional<Genre> genreOptional = genreRepository.findByGenre(genreCreateDTO.getGenre());
        if (genreOptional.isPresent()) {
            log.warn("genre is already added with id: {}", genreOptional.get().getId());
            throw GenreException.invalidParameter(GenreException.GENRE_ALREADY_ADDED);
        }
        return genreMapper.map2GenreDTO(genreRepository.save(genreMapper.map2Genre(genreCreateDTO)));
    }
}