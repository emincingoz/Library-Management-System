package com.emincingoz.librarymanagement.manager.user;

import com.emincingoz.librarymanagement.core.utilities.results.Result;
import com.emincingoz.librarymanagement.domain.requests.user.UserRegisterRequest;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> register(UserRegisterRequest userRegisterRequest);
}
