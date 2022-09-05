package com.emincingoz.mailservice.config;

import com.emincingoz.mailservice.domain.dtos.mail.MailDTO;
import com.emincingoz.mailservice.manager.mail.IMailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueMailConsumer {

    private final IMailService mailService;

    public QueueMailConsumer(IMailService mailService) {
        this.mailService = mailService;
    }

    @SneakyThrows
    @RabbitListener(queues = "${message-broker-queues.job-mail-create}")
    public void createReceive(@Payload String jsonMailDTO) {
        MailDTO mailDTO = mailDTOMapper(jsonMailDTO);

        this.mailService.createMail(mailDTO);
    }

    private MailDTO mailDTOMapper(String jsonMailDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        MailDTO  mailDTO= objectMapper.readValue(jsonMailDTO, MailDTO.class);
        return mailDTO;
    }
}
