package com.webapp.demo.service;

import com.webapp.demo.exceptions.ActivationMailException;
import com.webapp.demo.model.User;
import com.webapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SignUpService {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user){
        try{
            user.setActive(false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID());
            userService.save(user);
            mailService.sendActivationMessage(user);
        } catch (MailException mailException) {
            throw new ActivationMailException();
        }
        return user;
    }

}
