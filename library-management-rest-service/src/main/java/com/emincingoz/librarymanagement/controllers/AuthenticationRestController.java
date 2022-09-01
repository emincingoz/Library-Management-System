package com.emincingoz.librarymanagement.controllers;

import com.emincingoz.librarymanagement.domain.requests.authentication.UserLoginRequest;
import com.emincingoz.librarymanagement.manager.authentication.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken (@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(authenticationService.login(userLoginRequest));
    }
}
