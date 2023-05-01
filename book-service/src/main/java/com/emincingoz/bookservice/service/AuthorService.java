package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;

/**
 * Author service interface for Author operations
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface AuthorService {

    /**
     * Returns authorDTO by given author name
     * @param authorName
     * @return AuthorDTO
     */
    AuthorDTO getAuthorByAuthorName(String authorName);

    /**
     * Returns authorDTO list by given author name list
     * @param authorNameList
     * @return List<AuthorDTO>
     */
    List<AuthorDTO> getAuthorsByAuthorNameList(Collection<String> authorNameList);

    /**
     * Adds new author if the author is not already added. And return added author
     * @param authorCreateDTO
     * @return
     */
    AuthorDTO addAuthor(@Valid AuthorCreateDTO authorCreateDTO);
}
