package com.webapp.demo.service;

import com.webapp.demo.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailService {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @PostConstruct
    private void init(){
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(587);
        mailSender.setUsername("bud.trantow85@ethereal.email");
        mailSender.setPassword("7kdGtYGkc6YJyq7an7");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable","true");
        mailSender.setJavaMailProperties(properties);
    }

    public void sendActivationMessage(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@webapp.com");
        message.setTo(user.getEmail());
        message.setSubject("Activation Email");
        message.setText("http://localhost:5173/activation/"+user.getActivationToken());
        mailSender.send(message);
    }
}
