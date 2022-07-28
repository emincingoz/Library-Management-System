package com.emincingoz.librarymanagement.business.dtos;

import lombok.Data;

@Data
public class BookDTO {
    private String isbn;
    private String title;
    private String subject;
    private String publisher;
    private String language;
    private Long numberOfPages;
}
