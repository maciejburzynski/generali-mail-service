package com.generali.mailservice.mail;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Mail API", description = "Api for sending and getting sent emails")
public class MailRestController {

    private final MailService mailService;

    @Operation(summary = "Send* email", description = "*-save locally as a file")
    @ApiResponses({
            @ApiResponse(responseCode = "403", description = "Not Valid credentials"),
            @ApiResponse(responseCode = "401", description = "Not entitled enough"),
            @ApiResponse(responseCode = "200", description = "Valid credentials and mail sent")
    })
    @SecurityRequirement(name = "Basic Authentication")
    @PostMapping("/api/mails")
    ResponseEntity<?> sendMail(@RequestBody MailDto mailDto) {
        log.info("Receiving following MailDto:", mailDto);
        mailService.mapAndPersistMail(mailDto);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Get all mails to send or already sent", description = "")
    @ApiResponses({
            @ApiResponse(responseCode = "403", description = "Not Valid credentials"),
            @ApiResponse(responseCode = "401", description = "Not entitled enough"),
            @ApiResponse(responseCode = "200", description = "Valid credentials and mail sent")
    })
    @GetMapping("/api/mails")
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<?> getAllMails() {
        return ResponseEntity.status(200).body(mailService.getAllMails());
    }
}