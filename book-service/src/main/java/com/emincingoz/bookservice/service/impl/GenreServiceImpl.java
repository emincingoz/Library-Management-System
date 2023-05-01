package com.emincingoz.bookservice.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.emincingoz.bookservice.dto.GenreCreateDTO;
import com.emincingoz.bookservice.dto.GenreDTO;
import com.emincingoz.bookservice.exception.GenreExceptionUtility;
import com.emincingoz.bookservice.mapper.GenreMapper;
import com.emincingoz.bookservice.repository.GenreRepository;
import com.emincingoz.bookservice.repository.entity.Genre;
import com.emincingoz.bookservice.service.GenreService;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Genre service interface implementation
 *
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Log4j2
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    /**
     * Returns genre dto by given genre name
     *
     * @param genreName
     * @return GenreDTO
     */
    @Override
    public GenreDTO getGenreByGenreName(String genreName) {
        if (Strings.isNullOrEmpty(genreName)) {
            log.warn("genreName is null or empty");
            throw GenreExceptionUtility.invalidRequestException(GenreExceptionUtility.INVALID_PARAMETER);
        }
        Genre genre = genreRepository.findByGenre(genreName).orElseThrow(() -> {
            log.warn("genre is null or empty");
            throw GenreExceptionUtility.invalidRequestException(GenreExceptionUtility.GENRE_NOT_FOUND);
        });
        return genreMapper.map2GenreDTO(genre);
    }

    /**
     * Returns genre dto list by given genre name list
     *
     * @param genreNameList
     * @return List<GenreDTO>
     */
    @Override
    public List<GenreDTO> getGenresByGenreNameList(Collection<String> genreNameList) {
        if (CollectionUtils.isEmpty(genreNameList)) {
            log.warn("genreNameList is null or empty");
            throw GenreExceptionUtility.invalidRequestException(GenreExceptionUtility.INVALID_PARAMETER);
        }
        List<Genre> genreList = genreRepository.findGenresByGenreIn(genreNameList);
        if (CollectionUtils.isEmpty(genreList)) {
            log.warn("genreList is null or empty");
            throw GenreExceptionUtility.dataNotFoundException(GenreExceptionUtility.GENRE_NOT_FOUND);
        }
        return genreMapper.map2GenreDTOList(genreList);
    }

    /**
     * Adds new genre if genre is not already added. Then returns the added genre as dto
     *
     * @param genreCreateDTO
     * @return GenreDTO
     */
    @Override
    public GenreDTO addGenre(GenreCreateDTO genreCreateDTO) {
        if (genreCreateDTO == null) {
            log.warn("genreCreateDTO is null");
            throw GenreExceptionUtility.invalidRequestException(GenreExceptionUtility.INVALID_PARAMETER);
        }
        Optional<Genre> genreOptional = genreRepository.findByGenre(genreCreateDTO.getGenre());
        if (genreOptional.isPresent()) {
            log.warn("genre is already added with id: {}", genreOptional.get().getId());
            throw GenreExceptionUtility.invalidRequestException(GenreExceptionUtility.GENRE_ALREADY_ADDED);
        }
        return genreMapper.map2GenreDTO(genreRepository.save(genreMapper.map2Genre(genreCreateDTO)));
    }
}
