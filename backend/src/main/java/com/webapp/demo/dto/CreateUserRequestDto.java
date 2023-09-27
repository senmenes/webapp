package com.webapp.demo.dto;

import com.webapp.demo.validation.email.UniqueEmail;
import com.webapp.demo.validation.username.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    @NotBlank(message = "email cannot be null or empty")
    @Email(message = "Not valid email")
    @UniqueEmail
    private String email;
    @NotBlank(message = "Username cannot be null or empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
            message = "Username must be of 6 to 12 length with no special characters")
    @UniqueUsername
    private String userName;

    @Size(min = 6, max = 12, message = "Password must be of 6 to 12 length")
    @NotBlank(message = "Password cannot be null or empty")
    private String password;
}
