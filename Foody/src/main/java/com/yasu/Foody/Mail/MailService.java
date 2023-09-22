package com.yasu.Foody.Mail;

import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;

public interface MailService {
    String sendMail( String whoTO, String code) ;
    String sendMultiMediaMail() throws MessagingException;
}