package com.emincingoz.librarymanagement.dataAccess.abstracts;

import com.emincingoz.librarymanagement.business.dtos.BookDTO;
import com.emincingoz.librarymanagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT new com.emincingoz.librarymanagement.business.dtos.BookDTO"
            + " ( b.isbn, b.title, b.subject, b.publisher, b.language, b.numberOfPages, b.genre) "
            + " from Book b")
    List<BookDTO> getAll();

    Optional<Book> findByIsbn(String isbn);
}
