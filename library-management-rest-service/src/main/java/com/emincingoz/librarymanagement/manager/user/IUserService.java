package com.emincingoz.librarymanagement.manager.user;

import com.emincingoz.librarymanagement.domain.requests.user.UserRegisterRequest;
import com.emincingoz.librarymanagement.domain.requests.user.UserVerificationRequest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> register(UserRegisterRequest userRegisterRequest) throws UnirestException;

    ResponseEntity<?> verifyAccount(UserVerificationRequest userVerificationRequest);
}
