package com.emincingoz.bookservice.dto;

import com.emincingoz.bookservice.model.BindingType;
import com.emincingoz.bookservice.model.PaperType;
import com.emincingoz.bookservice.repository.entity.Author;
import com.emincingoz.bookservice.repository.entity.Genre;
import com.emincingoz.bookservice.repository.entity.Interpreter;
import com.emincingoz.bookservice.repository.entity.Publisher;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    @NotNull
    @NotEmpty
    @Max(value = 9223372036854775807L)
    @Min(value = 1l)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 13)
    private String isbn;

    @NotNull
    private String title;

    @NotNull
    @NotEmpty
    private Set<Author> authorList;

    private String subTitle;

    @NotNull
    private String originalName;

    private Set<Interpreter> interpreterList;

    private String subject;

    @Size(max = 50)
    private String printLanguage;

    @Size(max = 50)
    private String language;

    @NotNull
    @Max(value = 2147483647)
    @Min(value = 1)
    private Integer numberOfPages;

    @NotNull
    @NotEmpty
    private Set<Genre> genreList;

    @NotNull
    private Publisher publisher;

    private String publicationPlace;

    private PaperType paperType;

    private BindingType bindingType;

    private LocalDate releaseDate;

    private String estimatedReadingTime;

    private Double bookHeight;

    private Double bookWidth;
}
