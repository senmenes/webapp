package com.webapp.demo.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class GenericErrorResponseDto<T> {
    private T message;
    private HttpStatus returnCode;
}
