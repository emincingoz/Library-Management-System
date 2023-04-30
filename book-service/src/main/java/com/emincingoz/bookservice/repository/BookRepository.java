package com.emincingoz.bookservice.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.emincingoz.bookservice.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for BOOK entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Finds book by isbn
     * @param isbn
     * @return Optional<Book>
     */
    Optional<Book> findBookByIsbn(String isbn);

    /**
     * Finds book list by given author name inside Author class
     * @param author
     * @return List<Book>
     */
    List<Book> findByAuthorList_Author(String author);

    /**
     * Finds book list by given author name list
     * @param authors
     * @return List<Book>
     */
    List<Book> findBooksByAuthorList_AuthorIn(Collection<String> authors);

    /**
     * Finds book list by given publisher name in Publisher class
     * @param name
     * @return List<Book>
     */
    List<Book> findBooksByPublisher_Name(String name);


}