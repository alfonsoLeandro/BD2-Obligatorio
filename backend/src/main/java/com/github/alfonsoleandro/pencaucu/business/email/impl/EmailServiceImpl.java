package com.github.alfonsoleandro.pencaucu.business.email.impl;

import com.github.alfonsoleandro.pencaucu.business.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    public void sendEmail(String to, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(this.fromEmail);
        mailMessage.setTo(to);
        mailMessage.setSubject("PencaUCU");
        mailMessage.setText(message);

        this.emailSender.send(mailMessage);
    }

}
