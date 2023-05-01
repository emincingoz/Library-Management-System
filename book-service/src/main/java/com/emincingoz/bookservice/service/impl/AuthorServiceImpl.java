package com.emincingoz.bookservice.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import com.emincingoz.bookservice.exception.AuthorExceptionUtility;
import com.emincingoz.bookservice.mapper.AuthorMapper;
import com.emincingoz.bookservice.repository.AuthorRepository;
import com.emincingoz.bookservice.repository.entity.Author;
import com.emincingoz.bookservice.service.AuthorService;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Author Service Interface Implementation
 *
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Log4j2
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    /**
     * Returns specific author by author name
     *
     * @param authorName
     * @return AuthorDTO
     */
    @Override
    public AuthorDTO getAuthorByAuthorName(String authorName) {
        if (Strings.isNullOrEmpty(authorName)) {
            log.warn("authorName is null");
            throw AuthorExceptionUtility.invalidRequestException(AuthorExceptionUtility.INVALID_PARAMETER);
        }
        Author author = authorRepository.findByAuthor(authorName).orElseThrow(() -> {
            log.warn("author is null or empty");
            throw AuthorExceptionUtility.dataNotFoundException(AuthorExceptionUtility.AUTHOR_NOT_FOUND);
        });

        return authorMapper.map2AuthorDTO(author);
    }

    // TODO:: add controller class for getAuthorsByAuthorNameList method

    /**
     * Returns authorDTO list by author name list parameter
     *
     * @param authorNameList
     * @return List<AuthorDTO>
     */
    @Override
    public List<AuthorDTO> getAuthorsByAuthorNameList(Collection<String> authorNameList) {
        if (CollectionUtils.isEmpty(authorNameList)) {
            log.warn("authorNameList is null or empty");
            throw AuthorExceptionUtility.invalidRequestException(AuthorExceptionUtility.INVALID_PARAMETER);
        }
        List<Author> authorList = authorRepository.findAuthorsByAuthorIn(authorNameList);
        if (CollectionUtils.isEmpty(authorList)) {
            log.warn("authorList is null or empty");
            throw AuthorExceptionUtility.dataNotFoundException(AuthorExceptionUtility.AUTHOR_NOT_FOUND);
        }
        return authorMapper.map2AuthorDTOList(authorList);
    }

    /**
     * Adds new author to AUTHOR table if the author is not already added
     *
     * @param authorCreateDTO
     * @return AuthorDTO
     */
    @Override
    public AuthorDTO addAuthor(AuthorCreateDTO authorCreateDTO) {
        if (authorCreateDTO == null) {
            log.warn("authorCreateDTO is null");
            throw AuthorExceptionUtility.invalidRequestException(AuthorExceptionUtility.INVALID_PARAMETER);
        }
        Optional<Author> authorOptional = authorRepository.findByAuthor(authorCreateDTO.getAuthor());
        if (authorOptional.isPresent()) {
            log.warn("authorOptional is already added with id: {}", authorOptional.get().getId());
            throw AuthorExceptionUtility.invalidRequestException(AuthorExceptionUtility.AUTHOR_ALREADY_ADDED);
        }
        Author author = authorRepository.save(authorMapper.map2Author(authorCreateDTO));
        return authorMapper.map2AuthorDTO(author);
    }
}
