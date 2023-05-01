package com.emincingoz.bookservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * Entity class of PUBLISHER class
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PUBLISHER")
public class Publisher {
    @Id
    @Column(name = "PUBLISHER_ID", nullable = false, length = 16)
    @NotNull
    @SequenceGenerator(name = "PUBLISHER_ID_GENERATOR", sequenceName = "SEQ_PUBLISHER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PUBLISHER_ID_GENERATOR")
    private Long id;

    /**
     * Publisher name
     */
    @Column(name = "PUBLISHER_NAME")
    @NotNull
    @Size(max = 100)
    private String name;

    /**
     * List of books published by the publisher
     */
    @OneToMany(mappedBy = "publisher")
    @JsonBackReference  // to prevent infinite recursion
    private List<Book> bookList;
}
