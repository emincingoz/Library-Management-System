package com.emincingoz.bookservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDTO {

    @NotNull
    private Long id;

    @NotNull
    private String genre;
}
