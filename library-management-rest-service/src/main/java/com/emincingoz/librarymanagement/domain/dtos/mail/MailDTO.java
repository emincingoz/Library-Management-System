package com.emincingoz.librarymanagement.domain.dtos.mail;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailDTO {
    private String to;
    private String subject;
    private String body;
    private Instant sentDate;
}