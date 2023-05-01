package com.emincingoz.bookservice.controller;

import java.util.List;

import com.emincingoz.bookservice.controller.validation.WhiteSpaceChecker;
import com.emincingoz.bookservice.dto.BookCreateDTO;
import com.emincingoz.bookservice.dto.BookDTO;
import com.emincingoz.bookservice.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookService Book Controller Class
 *
 * @author Emin Cingoz
 * @version 4/25/2023
 */
@RestController
@RequestMapping("api/v1/book-service/book")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Get all books with pagination. Pageable and Page used.
     *
     * @param page
     * @param size
     * @return ResponseEntity<Page < BookDTO>>
     */
    @GetMapping("/get-all-books")
    public ResponseEntity<Page<BookDTO>> getAllBooks(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "size", required = false) Integer size) {
        return new ResponseEntity<>(bookService.getAllBooks(page, size), HttpStatus.OK);
    }

    /**
     * Finds book by book isbn parameter
     *
     * @param isbn
     * @return ResponseEntity<BookDTO>
     */
    @GetMapping("/get-book-by-isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<>(bookService.getBookByIsbn(isbn), HttpStatus.OK);
    }

    /**
     * Fetches all books of asked author
     *
     * @param author
     * @return ResponseEntity<List < BookDTO>>
     */
    @GetMapping("/get-books-by-author/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable("author") @WhiteSpaceChecker @NotNull String author) {
        return new ResponseEntity<>(bookService.getBooksByAuthor(author), HttpStatus.OK);
    }

    /**
     * PostMapping used for Get operation
     * Returns the books containing the given author list as a list.
     *
     * @param authorList
     * @return ResponseEntity<List < BookDTO>>
     */
    @PostMapping("/get-books-by-author-list")
    public ResponseEntity<List<BookDTO>> getBooksByAuthorList(@RequestBody List<String> authorList) {
        return new ResponseEntity<>(bookService.getBooksByAuthors(authorList), HttpStatus.OK);
    }

    /**
     * Returns the books of asked publisher name
     *
     * @param publisher
     * @return ResponseEntity<List < BookDTO>>
     */
    @GetMapping("/get-books-by-publisher/{publisher}")
    public ResponseEntity<List<BookDTO>> getBooksByPublisher(@PathVariable("publisher") @WhiteSpaceChecker @NotNull String publisher) {
        return new ResponseEntity<>(bookService.getBooksByPublisher(publisher), HttpStatus.OK);
    }

    /**
     * Adds new book to BOOK Table
     *
     * @param bookCreateDTO
     * @return ResponseEntity<BookDTO>
     */
    @PostMapping("/add-new-book")
    public ResponseEntity<BookDTO> addNewBook(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        return new ResponseEntity<>(bookService.addBook(bookCreateDTO), HttpStatus.CREATED);
    }
}
