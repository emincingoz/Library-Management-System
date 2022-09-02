package com.emincingoz.librarymanagement.domain.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String tckno;

    public UserRegisterRequest(String userName,
                               String firstName,
                               String lastName,
                               String password,
                               String email,
                               String tckno) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.tckno = tckno;
    }
}
