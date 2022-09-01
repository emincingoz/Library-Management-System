package com.emincingoz.librarymanagement.manager.user;

import com.emincingoz.librarymanagement.core.utilities.results.Result;
import com.emincingoz.librarymanagement.domain.requests.user.UserRegisterRequest;

public interface IUserService {
    Result register(UserRegisterRequest userRegisterRequest);
}
