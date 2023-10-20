package com.generali.mailservice.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MailRestController {

    private final MailService mailService;

    @PostMapping("/api/mails")
    ResponseEntity<?> sendMail(@RequestBody MailDto mailDto) {
        log.info("Trying to send: {}", mailDto);
        mailService.sendMail(mailDto);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/api/mails")
    ResponseEntity<?> getAllMails() {
        return ResponseEntity.status(200).body(mailService.getAllMails());
    }
}
