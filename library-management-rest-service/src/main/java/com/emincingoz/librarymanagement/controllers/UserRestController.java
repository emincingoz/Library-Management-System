package com.emincingoz.librarymanagement.controllers;

import com.emincingoz.librarymanagement.domain.dtos.user.UserVerificationDTO;
import com.emincingoz.librarymanagement.domain.requests.user.UserRegisterRequest;
import com.emincingoz.librarymanagement.domain.requests.user.UserVerificationRequest;
import com.emincingoz.librarymanagement.manager.user.IUserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) throws UnirestException {
        return userService.register(userRegisterRequest);
    }

    @PostMapping("/verifyAccount")
    public ResponseEntity<?> verifyAccount(@RequestBody UserVerificationRequest userVerificationRequest) {
        return userService.verifyAccount(userVerificationRequest);
    }
}
