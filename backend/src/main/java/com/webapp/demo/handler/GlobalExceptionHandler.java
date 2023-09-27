package com.webapp.demo.handler;


import com.webapp.demo.dto.ApiErrorDto;
import com.webapp.demo.dto.GenericErrorResponseDto;
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

//    @ExceptionHandler(DuplicateKeyException.class)
//    public ResponseEntity<ApiErrorDto>  duplicateKeyMongoDb(DuplicateKeyException exception){
//        ApiErrorDto apiErrorDto = new ApiErrorDto();
//        Map<String, String> errors = new HashMap<>();
//
//        if(exception.getMessage().contains("email")){
//            errors.put("email", "email is not unique");
//        } else if (exception.getMessage().contains("username")){
//            errors.put("userName", "username is not unique");
//        }
//        apiErrorDto.setMessage("Validation Error");
//        apiErrorDto.setPath(apiErrorDto.getPath());
//        apiErrorDto.setValidationErrors(errors);
//        return new ResponseEntity<>(apiErrorDto, HttpStatus.BAD_REQUEST);
//    }
}
