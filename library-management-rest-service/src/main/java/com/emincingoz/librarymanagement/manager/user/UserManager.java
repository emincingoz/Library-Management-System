package com.emincingoz.librarymanagement.manager.user;

import com.emincingoz.librarymanagement.core.utilities.BusinessRules;
import com.emincingoz.librarymanagement.core.utilities.results.ErrorResult;
import com.emincingoz.librarymanagement.core.utilities.results.Result;
import com.emincingoz.librarymanagement.core.utilities.results.SuccessResult;
import com.emincingoz.librarymanagement.domain.dtos.mail.MailDTO;
import com.emincingoz.librarymanagement.domain.models.Address;
import com.emincingoz.librarymanagement.domain.models.User;
import com.emincingoz.librarymanagement.domain.requests.user.UserRegisterRequest;
import com.emincingoz.librarymanagement.infrastructure.nationalityPeopleValidator.NationalityPeopleModel;
import com.emincingoz.librarymanagement.infrastructure.nationalityPeopleValidator.NationalityPeopleValidator;
import com.emincingoz.librarymanagement.manager.mail.IMailService;
import com.emincingoz.librarymanagement.repository.IUserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UserManager implements IUserService{

    private final IUserRepository userRepository;
    private final NationalityPeopleValidator nationalityPeopleValidator;
    private final ModelMapper modelMapper;

    private final IMailService mailService;

    public UserManager(IUserRepository userRepository,
                       @Qualifier("kpsPublicNationalityPeopleValidator") NationalityPeopleValidator nationalityPeopleValidator,
                       ModelMapper modelMapper,
                       IMailService mailService) {
        this.userRepository = userRepository;
        this.nationalityPeopleValidator = nationalityPeopleValidator;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
    }

    @Override
    public ResponseEntity<?> register(UserRegisterRequest userRegisterRequest) throws UnirestException {

        Result ruleResult = BusinessRules.run(
                isUserEmailExists(userRegisterRequest.getEmail()),
                isUserUserNameExists(userRegisterRequest.getUserName()));

        if (ruleResult != null)
            return new ResponseEntity<>(ruleResult, HttpStatus.CONFLICT);

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword()).substring(8);

        User user = new User(
                userRegisterRequest.getUserName(),
                userRegisterRequest.getFirstName(),
                userRegisterRequest.getLastName(),
                userRegisterRequest.getTckno(),
                userRegisterRequest.getEmail(),
                userRegisterRequest.getPhone(),
                encodedPassword,
                userRegisterRequest.getBirthYear()
        );

        // Return 417 Code Http
        NationalityPeopleModel nationalityPeopleModel = modelMapper.map(userRegisterRequest, NationalityPeopleModel.class);

        if (!nationalityPeopleValidator.validate(nationalityPeopleModel))
            return new ResponseEntity<>(
                    new ErrorResult(UserMessageConstants.USER_INFO_NOT_CORRECT),
                    HttpStatus.EXPECTATION_FAILED);

        sendVerificationEmail(user);

        // Return 423 Code Http
        // TODO:: Temporarily set to true, send activation code to e-mail address
        // TODO:: Return 423 Locked http response when user not activated
        user.setActivated(true);
        user.setDateOfMembership(LocalDateTime.now());
        user.setTotalBooksCheckedOut(Integer.toUnsignedLong(0));

        userRepository.save(user);

        return new ResponseEntity<>(new SuccessResult(UserMessageConstants.USER_REGISTER_SUCCESS), HttpStatus.ACCEPTED);
    }

    private void sendVerificationEmail(User user) {
        String verificationCode = UUID.randomUUID().toString().substring(1, 7);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);

        MailDTO mailDTO = new MailDTO();
        mailDTO.setTo(user.getEmail());
        mailDTO.setSubject("Yeni Üyelik Kaydı");
        mailDTO.setBody(currentTime + ": " + user.getEmail() + " mail adresi ile yeni kullanıcı oluşturulmuştur. Mail Adresini doğrulamak için kod: " + verificationCode);
        mailDTO.setSentDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).minusSeconds(60*60*3));
        mailService.sendMail(mailDTO);
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
