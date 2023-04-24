package com.emincingoz.bookservice.controller;

import com.emincingoz.bookservice.dto.AuthorCreateDTO;
import com.emincingoz.bookservice.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book-service/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/get-author-by-name/{authorName}")
    public ResponseEntity<?> getAuthorByAuthorName(@PathVariable("authorName") String authorName) {
        return new ResponseEntity<>(authorService.getAuthorByAuthorName(authorName), HttpStatus.OK);
    }

    @PostMapping("/add-new-author")
    public ResponseEntity<?> addNewAuthor(@Valid @RequestBody AuthorCreateDTO authorCreateDTO) {
        return new ResponseEntity<>(authorService.addAuthor(authorCreateDTO), HttpStatus.CREATED);
    }
}
