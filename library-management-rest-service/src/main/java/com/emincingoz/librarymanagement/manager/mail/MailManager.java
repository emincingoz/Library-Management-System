package com.emincingoz.librarymanagement.manager.mail;

import com.emincingoz.librarymanagement.domain.dtos.mail.MailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MailManager implements IMailService{

    private final RabbitTemplate rabbitTemplate;
    private final Queue jobMailCreateQueue;

    public MailManager(RabbitTemplate rabbitTemplate, @Qualifier("jobMailCreate") Queue jobMailCreateQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.jobMailCreateQueue = jobMailCreateQueue;
    }

    @SneakyThrows
    @Override
    public void sendMail(MailDTO mailDTO) {
        String mailDtoString = createMailDTOString(mailDTO);
        rabbitTemplate.convertAndSend(jobMailCreateQueue.getName(), mailDtoString);
    }

    private String createMailDTOString(MailDTO mailDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        String mailDtoString = objectMapper.writeValueAsString(mailDTO);
        return mailDtoString;
    }
}
