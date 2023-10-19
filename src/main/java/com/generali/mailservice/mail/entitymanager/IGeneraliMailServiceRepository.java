package com.generali.mailservice.mail.entitymanager;

import java.util.List;

import com.generali.mailservice.mail.Mail;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneraliMailServiceRepository {
  List<Mail> findAll();
}
