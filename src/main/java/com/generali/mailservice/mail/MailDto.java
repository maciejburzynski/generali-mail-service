package com.generali.mailservice.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailDto {

    @NotBlank(message = "Receiver must not be blank")
    private String receiver;
    @NotBlank(message = "Receiver must not be blank")
    private String subject;
    @NotBlank(message = "Receiver must not be blank")
    private String content;
}
