package com.generali.mailservice.mail;

public class MailTemplates {

  private static final String ACTIVATION_MAIL= """
                        To: %s
                        Subject: %s
                        Content: %s
                                    
                                    
                        Best Regards,
                        Generali's Mail Service :)
                        """;
//  static String getActivationMailTemplate(Mail mail){
//    return String.format(ACTIVATION_MAIL,
//      mail.getReceiver(),
//      mail.getSubject(),
//      mail.getContent());
//  }
}
