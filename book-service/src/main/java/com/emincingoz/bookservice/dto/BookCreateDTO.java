package com.emincingoz.bookservice.dto;

import com.emincingoz.bookservice.model.BindingType;
import com.emincingoz.bookservice.model.PaperType;
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
public class BookCreateDTO {

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 13)
    private String isbn;

    @NotNull
    private String title;

    @NotNull
    @NotEmpty
    private Set<String> authorList;

    private String subTitle;

    @NotNull
    private String originalName;

    private Set<String> interpreterList;

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
    private Set<String> genreList;

    @NotNull
    private String publisher;

    private String publicationPlace;

    private String paperType;

    private String bindingType;

    private LocalDate releaseDate;

    private String estimatedReadingTime;

    private Double bookHeight;

    private Double bookWidth;
}
