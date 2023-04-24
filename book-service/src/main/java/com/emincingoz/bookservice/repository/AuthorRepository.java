package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByAuthor(String authorName);

    List<Author> findAuthorsByAuthorIn(Collection<String> authorNameList);
}
