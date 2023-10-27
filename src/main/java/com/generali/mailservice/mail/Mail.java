package com.generali.mailservice.mail;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "mails")
@EqualsAndHashCode(of = "uuid")
@ToString
public class Mail {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid = UUID.randomUUID().toString();
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
