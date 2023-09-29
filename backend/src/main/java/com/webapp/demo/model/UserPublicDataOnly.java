package com.webapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPublicDataOnly {
    String id;
    String username;
    String email;
}
