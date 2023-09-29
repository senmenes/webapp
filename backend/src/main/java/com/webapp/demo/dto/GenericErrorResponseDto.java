package com.webapp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class GenericErrorResponseDto<T> {
    private T message;
    private HttpStatus returnCode;
}
