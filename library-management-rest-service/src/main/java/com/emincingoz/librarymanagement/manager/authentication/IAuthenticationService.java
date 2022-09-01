package com.emincingoz.librarymanagement.manager.authentication;

import com.emincingoz.librarymanagement.core.utilities.results.DataResult;
import com.emincingoz.librarymanagement.domain.dtos.authentication.TokenDTO;
import com.emincingoz.librarymanagement.domain.requests.authentication.UserLoginRequest;

public interface IAuthenticationService {

    DataResult<TokenDTO> login(UserLoginRequest userLoginRequest);
}
