package com.emincingoz.librarymanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lms_book")
public class Book {

    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    private String title;
    private String subject;
    private String publisher;
    private String language;

    @Column(name = "number_of_pages")
    private Long numberOfPages;

    private String genre;
}
