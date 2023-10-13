package com.generali.mailservice.mail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailRestController {

    @GetMapping("/api")
    String getHello() {
        return "Hello";
    }
}
