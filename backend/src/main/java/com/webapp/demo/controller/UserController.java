package com.webapp.demo.controller;

import com.webapp.demo.dto.CreateUserRequestDto;
import com.webapp.demo.model.User;
import com.webapp.demo.repository.UserRepository;
import com.webapp.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDto requestDto) {

        return ResponseEntity.ok(userService.createUser(User.builder()
                                .email(requestDto.getEmail())
                                .username(requestDto.getUserName())
                                .password(requestDto.getPassword())
                                .build()));
    }

}
