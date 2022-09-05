package com.emincingoz.mailservice.repository;

import com.emincingoz.mailservice.domain.dtos.mail.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface IMailRepository extends JpaRepository<Mail, Long> {

    @Query("select m from Mail m where m.sentDate < ?1")
    List<Mail> findAllSendingMails(Instant startDate);
}
