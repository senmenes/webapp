package com.webapp.demo.service;

import com.webapp.demo.dto.LoginRequestDto;
import com.webapp.demo.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean login(LoginRequestDto loginRequestDto){
        User user = userService.findByEmailOrUsername(loginRequestDto.getUsernameOrEmail());

        if(user != null){
            return passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());
        }
        return false;
    }
}
