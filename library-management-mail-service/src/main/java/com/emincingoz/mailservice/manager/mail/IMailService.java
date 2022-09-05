package com.emincingoz.mailservice.manager.mail;

import com.emincingoz.mailservice.domain.dtos.mail.MailDTO;

public interface IMailService {

    void createMail(MailDTO mailDTO);

    void sendAllMail();
}
