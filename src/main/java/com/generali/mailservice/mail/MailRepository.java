package com.generali.mailservice.mail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
}
