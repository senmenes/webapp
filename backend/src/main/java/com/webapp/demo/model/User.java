package com.webapp.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true, background = true)

    private String username;
    @Indexed(unique = true, background = true)
    private String email;
    private String password;

    private Boolean active;

    private UUID activationToken;
}
