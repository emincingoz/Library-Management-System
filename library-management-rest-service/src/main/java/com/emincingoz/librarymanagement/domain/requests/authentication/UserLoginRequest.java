package com.emincingoz.librarymanagement.domain.requests.authentication;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    private String userName;
    private String password;
}
