package com.webapp.demo.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String usernameOrEmail;
    private String password;
}
