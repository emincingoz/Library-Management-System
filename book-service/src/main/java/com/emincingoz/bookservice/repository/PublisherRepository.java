package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for PUBLISHER entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    /**
     * Finds publisher by given publisher name
     * @param publisherName
     * @return Optional<Publisher>
     */
    Optional<Publisher> findByName(String publisherName);
}
