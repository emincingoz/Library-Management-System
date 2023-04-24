package com.emincingoz.bookservice.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @Column(name = "AUTHOR_ID", nullable = false, length = 16)
    @NotNull
    @SequenceGenerator(name = "AUTHOR_ID_GENERATOR", sequenceName = "SEQ_AUTHOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_ID_GENERATOR")
    private Long id;

    @Column(name = "AUTHOR")
    @NotNull
    @Size(max = 100)
    private String author;

    @ManyToMany(mappedBy = "authorList")
    private Set<Book> bookList;
}
