package com.emincingoz.bookservice.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

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

    @Column(name = "PUBLISHER_NAME")
    @NotNull
    @Size(max = 100)
    private String name;
    
    @OneToMany(mappedBy = "publisher")
    private List<Book> bookList;
}
