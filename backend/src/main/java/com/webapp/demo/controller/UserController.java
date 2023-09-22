package com.webapp.demo.controller;

import com.webapp.demo.dto.CreateUserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto requestDto) {
        return ResponseEntity.ok(requestDto);
    }

}
