package com.emincingoz.mailservice.domain.dtos.mail.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "lms_mail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail_address")
    private String to;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @Column(name = "sent_date")
    private Instant sentDate;
}
