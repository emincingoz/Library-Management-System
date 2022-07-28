package com.emincingoz.librarymanagement.business.requests.book;

import lombok.Data;

@Data
public class CreateBookRequest {
    private Long isbn;
    private String title;
    private String subject;
    private String publisher;
    private String language;
    private Long numberOfPages;
}
