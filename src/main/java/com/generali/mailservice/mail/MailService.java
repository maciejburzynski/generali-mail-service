package com.generali.mailservice.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import static com.generali.mailservice.mail.MailUtils.GENERALI_DATE_FORMATTER;
import static java.util.Base64.getEncoder;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final MailRepository mailRepository;
    private MailMapper mailMapper = MailMapper.INSTANCE;

    @Value("${mail-service.mail.path}")
    private String mailPath;
    @Value("${mail-service.mail.extension}")
    private String mailExtension;

    void mapAndPersistMail(MailDto mailDto) {
        Mail mail = mailMapper.mapDtoToEntity(mailDto);
//        mailRepository.save(mail);
        log.info("Got MailDto: {} ,Mail to save: {}", mailDto, mail);
    }

    @Transactional
    void sendMail(Mail mail) {
        createMail(mail);
        mail.setStatus(MailStatus.SENT);
//        mailRepository.save(mail);
        log.info("Sent mail: {}", mail);
    }

    List<Mail> getAllMails(){
        return null;
    }

    List<Mail> getUnsentMails() {
    return mailRepository.findTop1ByStatus(MailStatus.NOT_SENT);
    }

    private void createMail(Mail mail) {
        File file = new File(getFIlePath(mail));
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(MailTemplates.getActivationMailTemplate(mail));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFIlePath(Mail mail) {
        String formattedMailSaveAtDate = mail.getSavedAt().format(DateTimeFormatter.ofPattern(GENERALI_DATE_FORMATTER));
        String fileName = formattedMailSaveAtDate + "-" + getEncoder().encode(mail.getReceiver().getBytes()) + mailExtension;
        return mailPath + "/" + fileName;
    }

}
