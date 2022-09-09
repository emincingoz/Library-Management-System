package com.emincingoz.librarymanagement.domain.requests.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVerificationRequest {

    private String userName;
    private String verificationCode;
}