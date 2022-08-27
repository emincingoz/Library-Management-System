package com.emincingoz.librarymanagement.api.controllers;

import com.emincingoz.librarymanagement.business.abstracts.BookService;
import com.emincingoz.librarymanagement.business.requests.book.CreateBookRequest;
import com.emincingoz.librarymanagement.business.requests.book.UpdateBookRequest;
import com.emincingoz.librarymanagement.core.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("v1/books")
    public ResponseEntity<?> getAllBooks() {
        Result result = bookService.getAllBooks();
        return ResponseEntity.ok(result);
    }

    @GetMapping("v1/books/get-by-isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        Result result = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(result);
    }

    /*@GetMapping("v1/books/search")
    public ResponseEntity<?> getBook(@RequestParam(value = "isbn", required = false) Optional<String> isbn,
                                     @RequestParam(value = "title", required = false) Optional<String> title,
                                     @RequestParam(value = "language", required = false) Optional<String> language,
                                     @RequestParam(value = "publisher", required = false) Optional<String> publisher,
                                     @RequestParam(value = "genre", required = false) Optional<String> genre) {
        Result books = bookService.getBook(isbn, title, language, publisher, genre);
        return ResponseEntity.ok(books);
    }*/

    @PostMapping("v1/books/add-book")
    public ResponseEntity<?> addNewBook(@RequestBody CreateBookRequest createBookRequest) {
        return ResponseEntity.ok(bookService.addNewBook(createBookRequest));
    }

    @PutMapping("v1/books/update-book/isbn={isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody UpdateBookRequest updateBookRequest) {
        Result result = bookService.updateBook(isbn, updateBookRequest);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("v1/books/delete-book-by-isbn/{isbn}")
    public ResponseEntity<?> deleteBookByIsbn(@PathVariable String isbn) {
        Result result = bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.ok(result);
    }
}
