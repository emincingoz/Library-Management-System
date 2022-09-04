package com.emincingoz.librarymanagement.infrastructure.nationalityPeopleValidator;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NationalityPeopleModel {
    private String tckno;
    private String firstName;
    private String lastName;
    private Integer birthYear;
}
