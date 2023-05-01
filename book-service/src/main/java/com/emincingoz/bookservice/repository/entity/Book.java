package com.emincingoz.bookservice.repository.entity;

import com.emincingoz.bookservice.model.BindingType;
import com.emincingoz.bookservice.model.PaperType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

/**
 * Entity class for BOOK Table
 * @author Emin Cingoz
 * @version 4/23/2023
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "ID", nullable = false, length = 16, unique = true)
    @NotNull
    @SequenceGenerator(name = "BOOK_ID_GENERATOR", sequenceName = "SEQ_BOOK", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_ID_GENERATOR")
    private Long id;

    /**
     * ISBN, books unique identifier
     */
    @Column(name = "ISBN", length = 13, unique = true)
    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;

    /**
     * Title of book
     */
    @Column(name = "TITLE", columnDefinition = "TEXT")
    @NotNull
    private String title;

    /**
     * Author list, a book can be written by more than one author
     */
    // cascade = CascadeType.PERSIST -> If there is no author while adding the book, it will be added as well.
    // However, if there is no author, the book was not added to table in service business.
    @NotNull
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "BOOK_AUTHORS", joinColumns = @JoinColumn(name = "AUTHOR_ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    @JsonManagedReference  // to prevent infinite recursion
    private Set<Author> authorList;

    /**
     * Book often have a subtitle
     * Example: "The Five-Hour Workday: Live Differently, Unlock Productivity, and Find Happiness" by Brenè Brown
     */
    @Column(name = "SUBTITLE", columnDefinition = "TEXT")
    private String subTitle;

    /**
     * Original name. Books may have translated into another languages or the publisher may have published the book under a different name
     */
    @Column(name = "ORIGINAL_NAME", columnDefinition = "TEXT")
    @NotNull
    private String originalName;

    /**
     * Interpreter list. Books may have translated from another language. In this case the book have translators.
     */
    @ManyToMany
    @JoinTable(name = "BOOK_INTERPRETERS", joinColumns = @JoinColumn(name = "INTERPRETER_ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    @JsonManagedReference  // to prevent infinite recursion
    private Set<Interpreter> interpreterList;

    /**
     * Subject. Books have subject
     */
    @Column(name = "SUBJECT", columnDefinition = "TEXT")
    private String subject;

    /**
     * Print language of the book
     */
    @Column(name = "PRINT_LANGUAGE", length = 50)
    @Size(max = 50)
    private String printLanguage;

    /**
     * Real language of the book. The language in which the author wrote the book
     */
    @Column(name = "LANGUAGE", length = 50)
    @Size(max = 50)
    private String language;

    /**
     * Number of pages of the book
     */
    @Column(name = "NUMBER_OF_PAGES")
    @NotNull
    @Max(value = 2147483647)
    private Integer numberOfPages;

    /**
     * Book genres. Books may be suitable for more than one genre.
     */
    @ManyToMany
    @JoinTable(name = "BOOK_GENRE", joinColumns = @JoinColumn(name = "GENRE_ID"), inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    @NotNull
    @JsonManagedReference  // to prevent infinite recursion
    private Set<Genre> genreList;

    /**
     * Publisher. Book has a publisher
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    @JsonManagedReference  // to prevent infinite recursion
    private Publisher publisher;

    /**
     * Where was the book printed. Not required information about the book
     */
    @Column(name = "PUBLICATION_PLACE")
    private String publicationPlace;

    /**
     * What is the paper type of the book. Not required
     */
    @Column(name = "PAPER_TYPE")
    @Enumerated(value = EnumType.STRING)
    private PaperType paperType;

    /**
     * What is the binding type of the book. Not required
     */
    @Column(name = "BINDING_TYPE")
    @Enumerated(value = EnumType.STRING)
    private BindingType bindingType;

    /**
     * Release date of book
     */
    @Column(name = "RELEASE_DATE")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDate;

    /**
     * Estimated reading time of book
     */
    @Column(name = "ESTIMATED_READING_TIME")
    private String estimatedReadingTime;

    /**
     * Books height
     */
    @Column(name = "BOOK_HEIGHT")
    private Double bookHeight;

    /**
     * Books width
     */
    @Column(name = "BOOK_WIDTH")
    private Double bookWidth;

}
