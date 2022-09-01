package com.emincingoz.librarymanagement.domain.requests.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {
    private String title;
    private String subject;
    private String publisher;
    private String language;
    private Long numberOfPages;
    private String genre;
}
