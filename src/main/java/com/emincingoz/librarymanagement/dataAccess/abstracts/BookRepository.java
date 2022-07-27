package com.emincingoz.librarymanagement.dataAccess.abstracts;

import com.emincingoz.librarymanagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
