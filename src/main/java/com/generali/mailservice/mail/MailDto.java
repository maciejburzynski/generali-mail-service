package com.generali.mailservice.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailDto {

    @Email
    private String receiver;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}
