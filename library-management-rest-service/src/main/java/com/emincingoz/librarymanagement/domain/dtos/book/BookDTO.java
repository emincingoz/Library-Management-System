package com.emincingoz.librarymanagement.domain.dtos.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String isbn;
    private String title;
    private String subject;
    private String publisher;
    private String language;
    private Long numberOfPages;
    private String genre;
}
