package com.emincingoz.librarymanagement.manager.authentication;

import com.emincingoz.librarymanagement.core.utilities.BusinessRules;
import com.emincingoz.librarymanagement.core.utilities.results.*;
import com.emincingoz.librarymanagement.domain.dtos.authentication.RefreshTokenDTO;
import com.emincingoz.librarymanagement.domain.dtos.authentication.TokenDTO;
import com.emincingoz.librarymanagement.domain.models.User;
import com.emincingoz.librarymanagement.domain.requests.authentication.UserLoginRequest;
import com.emincingoz.librarymanagement.repository.IUserRepository;
import com.emincingoz.librarymanagement.security.JwtTokenProvider;
import com.emincingoz.librarymanagement.security.JwtUserDetailsFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationManager implements IAuthenticationService{

    private final IUserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public DataResult<TokenDTO> login(UserLoginRequest userLoginRequest) {
        User user = this.userRepository.findByUserName(userLoginRequest.getUserName());
        var businessResult = BusinessRules.run(
                isUserExist(user),
                isUserActive(user),
                isInvalidPassword(user, userLoginRequest.getPassword()));
        if (businessResult != null) {
            return new DataErrorResult<>(businessResult.getMessage());
        }
        UserDetails userDetails = JwtUserDetailsFactory.create(user);
        String token = this.jwtTokenProvider.generateJwtToken(userDetails);

        RefreshTokenDTO refreshTokenDto = this.jwtTokenProvider.generateRefreshToken();

        user.setRefreshToken(refreshTokenDto.getToken());
        user.setRefreshTokenExpirationDate(refreshTokenDto.getExpirationDate());

        this.userRepository.save(user);

        TokenDTO tokenDTO = new TokenDTO(token, refreshTokenDto.getToken());

        return new DataSuccessResult<>(tokenDTO);
    }

    private Result isUserExist(User user) {
        if (user == null)
            return new ErrorResult(AuthenticationMessageConstants.USER_NOT_FOUND);

        return new SuccessResult();
    }

    // TODO:: If the user is not active, send the code for activation to the e-mail address, and it will be activated when the code is entered from the application.
    private Result isUserActive(User user) {
        if (user != null && !user.isActivated())
            return new ErrorResult(AuthenticationMessageConstants.USER_NOT_ACTIVE);
        return new SuccessResult();
    }

    private Result isInvalidPassword(User user, String password) {
        if (user == null) {
            return new ErrorResult(AuthenticationMessageConstants.USER_NOT_FOUND);
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        boolean result = encoder.matches(password, "{bcrypt}" + user.getPassword());

        if (!result) {
            return new ErrorResult(AuthenticationMessageConstants.USER_INVALID_PASSWORD);
        }
        return new SuccessResult();
    }
}
