package com.emincingoz.mailservice.infrastructure.mail;

import com.emincingoz.mailservice.domain.dtos.mail.model.Mail;

public interface IMailSendService {
    boolean sendMail(Mail mail);
}
