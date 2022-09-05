package com.emincingoz.mailservice.infrastructure.mail.smtp;

import com.emincingoz.mailservice.domain.dtos.mail.model.Mail;
import com.emincingoz.mailservice.infrastructure.mail.IMailSendService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpMailSendManager implements IMailSendService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    public SmtpMailSendManager(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendMail(Mail mail) {

        try {
            // Creating a simple mail message
            MimeMessage message = javaMailSender.createMimeMessage();

            // Setting up necessary details
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(),true);

            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
