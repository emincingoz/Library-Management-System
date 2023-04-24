package com.emincingoz.bookservice.controller;

import com.emincingoz.bookservice.dto.GenreCreateDTO;
import com.emincingoz.bookservice.service.GenreService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book-service/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/get-genre-by-name/{genre}")
    public ResponseEntity<?> getGenreByGenreName(@PathVariable("genre") String genre) {
        return new ResponseEntity<>(genreService.getGenreByGenreName(genre), HttpStatus.OK);
    }

    @PostMapping("/add-new-genre")
    public ResponseEntity<?> addNewGenre(@Valid @RequestBody GenreCreateDTO genreCreateDTO) {
        return new ResponseEntity<>(genreService.addGenre(genreCreateDTO), HttpStatus.CREATED);
    }
}
