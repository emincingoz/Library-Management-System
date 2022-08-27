package com.emincingoz.librarymanagement.business.requests.book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBookRequest {
    private String isbn;
    private String title;
    private String subject;
    private String publisher;
    private String language;
    private Long numberOfPages;
    private String genre;
}
