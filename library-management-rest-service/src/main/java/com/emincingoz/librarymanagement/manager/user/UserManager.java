package com.emincingoz.librarymanagement.manager.user;

import com.emincingoz.librarymanagement.core.utilities.BusinessRules;
import com.emincingoz.librarymanagement.core.utilities.results.ErrorResult;
import com.emincingoz.librarymanagement.core.utilities.results.Result;
import com.emincingoz.librarymanagement.core.utilities.results.SuccessResult;
import com.emincingoz.librarymanagement.domain.models.Address;
import com.emincingoz.librarymanagement.domain.models.User;
import com.emincingoz.librarymanagement.domain.requests.user.UserRegisterRequest;
import com.emincingoz.librarymanagement.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserManager implements IUserService{

    private final IUserRepository userRepository;

    @Override
    public Result register(UserRegisterRequest userRegisterRequest) {

        Result ruleResult = BusinessRules.run(
                isUserEmailExists(userRegisterRequest.getEmail()),
                isUserUserNameExists(userRegisterRequest.getUserName()));

        if (ruleResult != null)
            return ruleResult;

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword()).substring(8);

        User user = new User(
                userRegisterRequest.getUserName(),
                userRegisterRequest.getFirstName(),
                userRegisterRequest.getLastName(),
                userRegisterRequest.getTckno(),
                userRegisterRequest.getEmail(),
                userRegisterRequest.getPhone(),
                encodedPassword
        );

        // TODO:: Temporarily set to true, send activation code to e-mail address
        user.setActivated(true);
        user.setDateOfMembership(LocalDateTime.now());
        user.setTotalBooksCheckedOut(Integer.toUnsignedLong(0));

        userRepository.save(user);

        return new SuccessResult(UserMessageConstants.USER_REGISTER_SUCCESS);
    }

    private Result isUserEmailExists(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            return new ErrorResult(UserMessageConstants.USER_REGISTER_ERROR_EMAIL_EXIST);
        }
        return new SuccessResult();
    }

    private Result isUserUserNameExists(String userName) {
        User user = this.userRepository.findByUserName(userName);
        if (user != null) {
            return new ErrorResult(UserMessageConstants.USER_REGISTER_ERROR_USERNAME_EXIST);
        }
        return new SuccessResult();
    }
}
