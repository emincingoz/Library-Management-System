package com.emincingoz.librarymanagement.api.controllers;

import com.emincingoz.librarymanagement.business.abstracts.BookService;
import com.emincingoz.librarymanagement.entities.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
@Api(value = "My Book Api Implementation")
public class BookController {

    private final BookService bookService;

    @GetMapping()
    @ApiOperation(value = "Get Mapping")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("add")
    @ApiOperation(value = "Post Mapping")
    public ResponseEntity<?> addNewBook(@RequestBody @ApiParam(value = "param 1") Book book) {
        return ResponseEntity.ok(bookService.addNewBook(book));
    }
}
