package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface of AUTHOR entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Finds author by author name
     * @param authorName
     * @return Optional<Author>
     */
    Optional<Author> findByAuthor(String authorName);

    /**
     * Finds authors by given author name list
     * @param authorNameList
     * @return List<Author>
     */
    List<Author> findAuthorsByAuthorIn(Collection<String> authorNameList);
}
