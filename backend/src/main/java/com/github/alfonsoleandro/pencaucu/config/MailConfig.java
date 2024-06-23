package com.github.alfonsoleandro.pencaucu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@Configuration
public class MailConfig {

    private final String emailPassword;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public MailConfig() throws IOException {
        this.emailPassword = Files.readString(Path.of(System.getenv("EMAIL_PASSWORD_PATH")));
    }


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp-mail.outlook.com");
        mailSender.setPort(587);

        mailSender.setUsername(this.fromEmail);
        mailSender.setPassword(this.emailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
