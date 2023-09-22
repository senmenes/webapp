package com.webapp.demo.handler;

import com.webapp.demo.dto.GenericErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidEx(MethodArgumentNotValidException exception){
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(i -> errors.add(i.getDefaultMessage()));

        return ResponseEntity.badRequest().body(GenericErrorResponseDto.builder().message(errors.get(0)).returnCode(HttpStatus.BAD_REQUEST).build());
    }
}
