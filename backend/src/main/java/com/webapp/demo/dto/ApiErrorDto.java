package com.webapp.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ApiErrorDto {
    String path;
    String message;
    Map<String,String> validationErrors;
}
