package com.emincingoz.bookservice.repository.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class of GENRE table
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @Column(name = "GENRE_ID", nullable = false, length = 16)
    @NotNull
    @SequenceGenerator(name = "GENRE_ID_GENERATOR", sequenceName = "SEQ_GENRE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_ID_GENERATOR")
    private Long id;

    /**
     * Book genre
     */
    @Column(name = "GENRE")
    @NotNull
    private String genre;

    /**
     * List of book written in that genre
     */
    @ManyToMany(mappedBy = "genreList")
    @JsonBackReference  // to prevent infinite recursion
    private Set<Book> bookList;
}
