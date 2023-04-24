package com.emincingoz.bookservice.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @Column(name = "GENRE_ID", nullable = false, length = 16)
    @NotNull
    @SequenceGenerator(name = "GENRE_ID_GENERATOR", sequenceName = "SEQ_GENRE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_ID_GENERATOR")
    private Long id;

    @Column(name = "GENRE")
    @NotNull
    private String genre;

    @ManyToMany(mappedBy = "genreList")
    private Set<Book> bookList;
}
