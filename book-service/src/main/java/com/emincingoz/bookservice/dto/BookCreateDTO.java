package com.emincingoz.bookservice.dto;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private Set<String> authorList;

    private String subTitle;

    @NotNull
    @NotEmpty
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
    @NotEmpty
    private String publisher;

    private String publicationPlace;

    private String paperType;

    private String bindingType;

    private LocalDate releaseDate;

    private String estimatedReadingTime;

    private Double bookHeight;

    private Double bookWidth;
}
