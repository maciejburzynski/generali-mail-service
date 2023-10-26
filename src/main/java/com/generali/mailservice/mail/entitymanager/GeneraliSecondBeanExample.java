package com.generali.mailservice.mail.entitymanager;

import java.util.List;

import com.generali.mailservice.mail.Mail;
import org.springframework.stereotype.Repository;

@Repository
public class GeneraliSecondBeanExample implements IGeneraliMailServiceRepository{
  @Override
  public List<Mail> findAll() {
    return null;
  }
}
