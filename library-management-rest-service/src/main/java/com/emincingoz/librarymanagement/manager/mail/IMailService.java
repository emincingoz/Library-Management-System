package com.emincingoz.librarymanagement.manager.mail;

import com.emincingoz.librarymanagement.domain.dtos.mail.MailDTO;

public interface IMailService {

    void sendMail(MailDTO mailDTO);
}
