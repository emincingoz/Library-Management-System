package com.emincingoz.bookservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreCreateDTO {
    @NotNull
    private String genre;
}
