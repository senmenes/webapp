package com.webapp.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    @NotBlank(message = "email cannot be null or empty")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "Not valid email")
    private String email;
    @NotBlank(message = "Username cannot be null or empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
            message = "username must be of 6 to 12 length with no special characters")
    private String userName;
    @NotBlank(message = "password cannot be null or empty")
    private String password;
}
