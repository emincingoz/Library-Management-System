package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;

public interface AuthorService {
    AuthorDTO getAuthorByAuthorName(String authorName);
    List<AuthorDTO> getAuthorsByAuthorNameList(Collection<String> authorNameList);
    AuthorDTO addAuthor(@Valid AuthorCreateDTO authorCreateDTO);
}
