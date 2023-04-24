package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.BookCreateDTO;
import com.emincingoz.bookservice.dto.BookDTO;

import java.util.List;
import java.util.Set;

public interface BookService {

    // TODO:: getAllBooks (Pagination)
    Set<BookDTO> getAllBooks();

    BookDTO getBookByIsbn(String isbn);

    List<BookDTO> getBooksByAuthor(String author);

    List<BookDTO> getBooksByAuthors(List<String> authorList);

    List<BookDTO> getBooksByPublisher(String publisher);

    BookDTO addBook(BookCreateDTO bookCreateDTO);
}
