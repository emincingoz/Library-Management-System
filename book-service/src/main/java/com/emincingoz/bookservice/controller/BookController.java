package com.emincingoz.bookservice.controller;

import com.emincingoz.bookservice.dto.BookCreateDTO;
import com.emincingoz.bookservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book-service/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // TODO:: Pagination needed
    @GetMapping("/get-all-books")
    public ResponseEntity<?> getAllBooks() {
        return null;
    }

    @GetMapping("/get-book-by-isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<>(bookService.getBookByIsbn(isbn), HttpStatus.OK);
    }

    @GetMapping("/get-books-by-author/{author}")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable("author") String author) {
        return new ResponseEntity<>(bookService.getBooksByAuthor(author), HttpStatus.OK);
    }

    /**
     * PostMapping used for Get operation
     * Returns the books containing the given author list as a list.
     * @param authorList
     * @return
     */
    @PostMapping("/get-books-by-author-list")
    public ResponseEntity<?> getBooksByAuthorList(@RequestBody List<String> authorList) {
        return new ResponseEntity<>(bookService.getBooksByAuthors(authorList), HttpStatus.OK);
    }

    @GetMapping("/get-books-by-publisher/{publisher}")
    public ResponseEntity<?> getBooksByPublisher(@PathVariable("publisher") String publisher) {
        return new ResponseEntity<>(bookService.getBooksByPublisher(publisher), HttpStatus.OK);
    }

    @PostMapping("/add-new-book")
    public ResponseEntity<?> addNewBook(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        return new ResponseEntity<>(bookService.addBook(bookCreateDTO), HttpStatus.CREATED);
    }
}
