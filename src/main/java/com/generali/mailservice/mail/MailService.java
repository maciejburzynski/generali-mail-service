package com.generali.mailservice.mail;

import com.generali.mailservice.mail.entitymanager.GeneraliMailServiceRepository;
import com.generali.mailservice.mail.entitymanager.IGeneraliMailServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final MailRepository mailRepository;
    private final GeneraliMailServiceRepository customRepository;
    MailMapper mailMapper = MailMapper.INSTANCE;

    public void sendMail(MailDto mailDto) {
        Mail mail = mailMapper.mapDtoToEntity(mailDto);
        sendMail(mail);
        mailRepository.save(mail);
        log.info("Mail to save: {}", mail);
    }

    List<Mail> getAllMails(){
        return customRepository.findAll();
    }
    private void sendMail(Mail mail) {
        File file = new File(getFIlePath(mail));
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(mailTempate(mail));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFIlePath(Mail mail) {
        String DATE_FORMAT = mail.getSavedAt().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSSSSS"));
        String BASIC_PATH = "/Users/bum5wz/Desktop/Mails/";
        String FILE_NAME = DATE_FORMAT + "-" + Base64.getEncoder().encode(mail.getReceiver().getBytes()) + ".txt";
        return BASIC_PATH + "/" + FILE_NAME;

    }

    private String mailTempate(Mail mail) {
        return String.format("""
                        To: %s
                        Subject: %s
                        Content: %s
                                    
                                    
                        Best Regards,
                        Generali's Mail Service :)
                        """,
                mail.getReceiver(),
                mail.getSubject(),
                mail.getContent()
        );
    }


}
