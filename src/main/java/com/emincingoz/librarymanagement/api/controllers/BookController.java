package com.emincingoz.librarymanagement.api.controllers;

import com.emincingoz.librarymanagement.business.abstracts.BookService;
import com.emincingoz.librarymanagement.business.dtos.BookDTO;
import com.emincingoz.librarymanagement.business.requests.book.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<?> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("new-book-entry")
    public ResponseEntity<?> addNewBook(@RequestBody CreateBookRequest createBookRequest) {
        bookService.addNewBook(createBookRequest);
        return ResponseEntity.ok("Ok");
    }
}
