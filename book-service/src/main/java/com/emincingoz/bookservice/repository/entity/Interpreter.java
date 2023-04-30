package com.emincingoz.bookservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Entity class of INTERPRETER table
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INTERPRETER")
public class Interpreter {
    @Id
    @Column(name = "INTERPRETER_ID", nullable = false, length = 16)
    @NotNull
    @SequenceGenerator(name = "INTERPRETER_ID_GENERATOR", sequenceName = "SEQ_INTERPRETER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INTERPRETER")
    private Long id;

    /**
     * Interpreter name
     */
    @Column(name = "INTERPRETER")
    @NotNull
    @Size(max = 100)
    private String interpreter;

    /**
     * List of books translated by the interpreter
     */
    @ManyToMany(mappedBy = "interpreterList")
    @JsonBackReference  // to prevent infinite recursion
    private Set<Book> bookList;
}
