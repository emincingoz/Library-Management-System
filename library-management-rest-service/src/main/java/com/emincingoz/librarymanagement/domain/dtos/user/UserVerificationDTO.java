package com.emincingoz.librarymanagement.domain.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVerificationDTO {

    private String userName;
    private String verificationCode;
}
