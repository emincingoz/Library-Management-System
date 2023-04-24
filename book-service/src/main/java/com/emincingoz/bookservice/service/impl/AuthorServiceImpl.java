package com.emincingoz.bookservice.service.impl;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import com.emincingoz.bookservice.exception.AuthorException;
import com.emincingoz.bookservice.mapper.AuthorMapper;
import com.emincingoz.bookservice.repository.AuthorRepository;
import com.emincingoz.bookservice.repository.entity.Author;
import com.emincingoz.bookservice.service.AuthorService;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDTO getAuthorByAuthorName(String authorName) {
        if (Strings.isNullOrEmpty(authorName)) {
            log.warn("authorName is null");
            throw AuthorException.invalidParameter(AuthorException.INVALID_PARAMETER);
        }
        Author author = authorRepository.findByAuthor(authorName).orElseThrow(() -> {
            log.warn("author is null or empty");
            throw AuthorException.dataNotFoundException(AuthorException.AUTHOR_NOT_FOUND);
        });
        return authorMapper.map2AuthorDTO(author);
    }

    @Override
    public List<AuthorDTO> getAuthorsByAuthorNameList(Collection<String> authorNameList) {
        if (CollectionUtils.isEmpty(authorNameList)) {
            log.warn("authorNameList is null or empty");
            throw AuthorException.invalidParameter(AuthorException.INVALID_PARAMETER);
        }
        List<Author> authorList = authorRepository.findAuthorsByAuthorIn(authorNameList);
        if (CollectionUtils.isEmpty(authorList)) {
            log.warn("authorList is null or empty");
            throw AuthorException.dataNotFoundException(AuthorException.AUTHOR_NOT_FOUND);
        }
        return authorMapper.map2AuthorDTOList(authorList);
    }

    @Override
    public AuthorDTO addAuthor(AuthorCreateDTO authorCreateDTO) {
        if (authorCreateDTO == null) {
            log.warn("authorCreateDTO is null");
            throw AuthorException.invalidParameter(AuthorException.INVALID_PARAMETER);
        }
        Optional<Author> authorOptional = authorRepository.findByAuthor(authorCreateDTO.getAuthor());
        if (authorOptional.isPresent()) {
            log.warn("authorOptional is already added with id: {}", authorOptional.get().getId());
            throw AuthorException.invalidParameter(AuthorException.AUTHOR_ALREADY_ADDED);
        }
        return authorMapper.map2AuthorDTO(authorRepository.save(authorMapper.map2Author(authorCreateDTO)));
    }
}
