package com.generali.mailservice.mail;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "mails")
public class Mail {

    @Id
    @GeneratedValue
    private Long id;
    private String receiver;
    private String subject;
    private String content;
    private LocalDateTime savedAt;
    @Enumerated(value = EnumType.STRING)
    private MailStatus status;
    private LocalDateTime sentAt;

    public Mail(String receiver, String subject, String content) {
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.savedAt = LocalDateTime.now();
        this.status = MailStatus.NOT_SENT;
    }

    public Mail() {
        this.savedAt = LocalDateTime.now();
        this.status = MailStatus.NOT_SENT;
    }
}
