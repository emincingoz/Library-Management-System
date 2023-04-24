package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByAuthorListContains(String author);

    List<Book> findBooksByAuthorList_AuthorIn(Collection<String> authors);

    List<Book> findBooksByPublisher_Name(String name);


}