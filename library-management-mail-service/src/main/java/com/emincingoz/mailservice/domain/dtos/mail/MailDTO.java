package com.emincingoz.mailservice.domain.dtos.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String to;
    private String subject;
    private String body;
    private Instant sentDate;
}
