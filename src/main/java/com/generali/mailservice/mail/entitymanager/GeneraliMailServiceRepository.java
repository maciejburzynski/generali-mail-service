package com.generali.mailservice.mail.entitymanager;

import java.util.List;

import com.generali.mailservice.mail.Mail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GeneraliMailServiceRepository implements IGeneraliMailServiceRepository {


  @PersistenceContext
  private EntityManager entityManager;
  @Override
  public List<Mail> findAll() {
    return (List<Mail>) entityManager.createQuery("FROM Mail").getResultList();
  }



}
