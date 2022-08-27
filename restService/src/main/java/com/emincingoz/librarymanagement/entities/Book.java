package com.emincingoz.librarymanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
