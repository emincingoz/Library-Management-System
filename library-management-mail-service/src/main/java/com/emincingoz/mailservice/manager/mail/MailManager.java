package com.emincingoz.mailservice.manager.mail;

import com.emincingoz.mailservice.domain.dtos.mail.MailDTO;
import com.emincingoz.mailservice.domain.dtos.mail.model.Mail;
import com.emincingoz.mailservice.infrastructure.mail.IMailSendService;
import com.emincingoz.mailservice.repository.IMailRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class MailManager implements IMailService{

    private final ModelMapper modelMapper;
    private final IMailRepository mailRepository;
    private final IMailSendService mailSendService;

    Logger logger= LoggerFactory.getLogger(MailManager.class);

    public MailManager(ModelMapper modelMapper, IMailRepository mailRepository, IMailSendService mailSendService) {
        this.modelMapper = modelMapper;
        this.mailRepository = mailRepository;
        this.mailSendService = mailSendService;
    }

    @Override
    public void createMail(MailDTO mailDTO) {
        Mail mail = modelMapper.map(mailDTO, Mail.class);
        mailRepository.save(mail);
    }

    @Override
    @Scheduled(fixedDelayString = "${job-settings.mail-send-interval}")
    @Transactional
    public void sendAllMail() {
        List<Mail> mails = mailRepository.findAllSendingMails(Instant.now());

        logger.info("MailManager.sendAllMail() mails.size(): " + mails.size());

        mails.stream().forEach(e -> {
            boolean result = mailSendService.sendMail(e);

            if (result) {
                logger.info("Mail sent successfully. Email: " + e.getTo());
                mailRepository.delete(e);
            }
            else {
                logger.error("Mail sent failed. Email: " + e.getTo());
            }

        });
    }
}
