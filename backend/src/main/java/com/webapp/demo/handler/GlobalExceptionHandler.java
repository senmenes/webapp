package com.webapp.demo.handler;


import com.webapp.demo.dto.ApiErrorDto;
import com.webapp.demo.dto.GenericErrorResponseDto;
import com.webapp.demo.exceptions.ActivationMailException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> methodArgumentNotValidEx(MethodArgumentNotValidException exception){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        apiErrorDto.setMessage("Validation Error");
        apiErrorDto.setPath(apiErrorDto.getPath());
        apiErrorDto.setValidationErrors(errors);
        return new ResponseEntity<>(apiErrorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ActivationMailException.class)
    public ResponseEntity<ApiErrorDto> activationMailEx(ActivationMailException exception){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        Map<String, String> errors = new HashMap<>();
        errors.put("general", exception.getMessage());

        apiErrorDto.setMessage("Mail Error");
        apiErrorDto.setPath(apiErrorDto.getPath());
        apiErrorDto.setValidationErrors(errors);
        return new ResponseEntity<>(apiErrorDto, HttpStatus.BAD_GATEWAY);
    }
}
