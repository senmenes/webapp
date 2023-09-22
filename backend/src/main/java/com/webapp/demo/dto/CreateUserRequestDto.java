package com.webapp.demo.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    private String email;
    private String userName;
    private String password;
}
