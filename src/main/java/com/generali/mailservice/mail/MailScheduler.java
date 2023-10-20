package com.generali.mailservice.mail;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailScheduler {

  private final MailService mailService;
  @Scheduled(cron = "${mail-service.activate.cron}")
  void scheduledSendMail() {
    List<Mail> mailsToSend = mailService.getMailsToSend();
    mailsToSend
      .stream()
      .peek(mail -> log.info(mail.toString()))
      .forEach(mail -> mailService.sendScheduledMail(mail));

  }

}
