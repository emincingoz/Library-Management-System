package com.emincingoz.bookservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterpreterCreateDTO {
    @NotNull
    @Size(max = 100)
    private String interpreter;
}
