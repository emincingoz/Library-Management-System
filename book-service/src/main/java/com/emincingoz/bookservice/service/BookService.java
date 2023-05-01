package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.BookCreateDTO;
import com.emincingoz.bookservice.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Book service interface for book operations
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface BookService {

    /**
     * Returns book list as page if books are added before
     * @param page
     * @param size
     * @return Page<BookDTO>
     */
    Page<BookDTO> getAllBooks(Integer page, Integer size);

    /**
     * Returns book by given book isbn
     * @param isbn
     * @return BookDTO
     */
    BookDTO getBookByIsbn(String isbn);

    /**
     * Returns book list by given book author name
     * @param author
     * @return List<BookDTO>
     */
    List<BookDTO> getBooksByAuthor(String author);

    /**
     * Returns book list by given author name list
     * @param authorList
     * @return List<BookDTO>
     */
    List<BookDTO> getBooksByAuthors(List<String> authorList);

    /**
     * Returns book list by book publisher name
     * @param publisher
     * @return List<BookDTO>
     */
    List<BookDTO> getBooksByPublisher(String publisher);

    /**
     * Adds new book if the book is not already added. Then returns the added book as dto
     * @param bookCreateDTO
     * @return BookDTO
     */
    BookDTO addBook(BookCreateDTO bookCreateDTO);
}
