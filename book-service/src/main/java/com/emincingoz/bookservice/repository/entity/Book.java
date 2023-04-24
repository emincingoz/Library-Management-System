package com.emincingoz.bookservice.repository.entity;

import com.emincingoz.bookservice.model.BindingType;
import com.emincingoz.bookservice.model.PaperType;
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

    @Column(name = "ISBN", length = 13, unique = true)
    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;

    @Column(name = "TITLE", columnDefinition = "TEXT")
    @NotNull
    private String title;

    @NotNull
    @ManyToMany
    @JoinTable(name = "BOOK_AUTHORS", joinColumns = @JoinColumn(name = "AUTHOR_ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    private Set<Author> authorList;

    @Column(name = "SUBTITLE", columnDefinition = "TEXT")
    private String subTitle;

    @Column(name = "ORIGINAL_NAME", columnDefinition = "TEXT")
    @NotNull
    private String originalName;

    @ManyToMany
    @JoinTable(name = "BOOK_INTERPRETERS", joinColumns = @JoinColumn(name = "INTERPRETER_ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    private Set<Interpreter> interpreterList;

    @Column(name = "SUBJECT", columnDefinition = "TEXT")
    private String subject;

    @Column(name = "PRINT_LANGUAGE", length = 50)
    @Size(max = 50)
    private String printLanguage;

    @Column(name = "LANGUAGE", length = 50)
    @Size(max = 50)
    private String language;

    @Column(name = "NUMBER_OF_PAGES")
    @NotNull
    @Max(value = 2147483647)
    private Integer numberOfPages;

    @ManyToMany
    @JoinTable(name = "BOOK_GENRE", joinColumns = @JoinColumn(name = "GENRE_ID"), inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    @NotNull
    private Set<Genre> genreList;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @Column(name = "PUBLICATION_PLACE")
    private String publicationPlace;

    @Column(name = "PAPER_TYPE")
    @Enumerated(value = EnumType.STRING)
    private PaperType paperType;

    @Column(name = "BINDING_TYPE")
    @Enumerated(value = EnumType.STRING)
    private BindingType bindingType;

    @Column(name = "RELEASE_DATE")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDate;

    @Column(name = "ESTIMATED_READING_TIME")
    private String estimatedReadingTime;

    @Column(name = "BOOK_HEIGHT")
    private Double bookHeight;

    @Column(name = "BOOK_WIDTH")
    private Double bookWidth;

}
