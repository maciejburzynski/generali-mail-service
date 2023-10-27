package com.generali.mailservice.mail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MailRepository extends JpaRepository<Mail, Long> {
  List<Mail> findTop1ByStatus(MailStatus status);
}
