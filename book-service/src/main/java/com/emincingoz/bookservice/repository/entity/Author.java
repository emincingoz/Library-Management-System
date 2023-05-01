package com.emincingoz.bookservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

/**
 * Entity class for AUTHOR table
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @Column(name = "AUTHOR_ID", nullable = false, length = 16)
    @NotNull
    @SequenceGenerator(name = "AUTHOR_ID_GENERATOR", sequenceName = "SEQ_AUTHOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_ID_GENERATOR")
    private Long id;

    /**
     * Author name
     */
    @Column(name = "AUTHOR")
    @NotNull
    @Size(max = 100)
    private String author;

    /**
     * List of books written by the author
     */
    @ManyToMany(mappedBy = "authorList", fetch = FetchType.LAZY)
    @JsonBackReference  // to prevent infinite recursion
    private Set<Book> bookList;
}
