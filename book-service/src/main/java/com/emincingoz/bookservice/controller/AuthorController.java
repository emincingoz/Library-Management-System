package com.emincingoz.bookservice.controller;

import com.emincingoz.bookservice.controller.validation.WhiteSpaceChecker;
import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.dto.AuthorDTO;
import com.emincingoz.bookservice.service.AuthorService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Book Service Author Controller Class
 *
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@RestController
@RequestMapping("api/v1/book-service/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Gets specific author by author name
     *
     * @param authorName
     * @return ResponseEntity<AuthorDTO>
     */
    @GetMapping("/get-author-by-name/{authorName}")
    public ResponseEntity<AuthorDTO> getAuthorByAuthorName(@PathVariable("authorName") @WhiteSpaceChecker String authorName) {
        return new ResponseEntity<>(authorService.getAuthorByAuthorName(authorName), HttpStatus.OK);
    }

    /**
     * Adds new author if the author is not already added
     *
     * @param authorCreateDTO
     * @return ResponseEntity<AuthorDTO>
     */
    @PostMapping("/add-new-author")
    public ResponseEntity<AuthorDTO> addNewAuthor(@Valid @RequestBody AuthorCreateDTO authorCreateDTO) {
        return new ResponseEntity<>(authorService.addAuthor(authorCreateDTO), HttpStatus.CREATED);
    }
}
