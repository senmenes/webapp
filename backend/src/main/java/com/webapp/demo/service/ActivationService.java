package com.webapp.demo.service;

import com.webapp.demo.dto.GenericErrorResponseDto;
import com.webapp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivationService {

    @Autowired
    UserService userService;

    public GenericErrorResponseDto<String> activateUser(String token){
        try {
            if (userService.findByActivationTokenAndActivateUser(UUID.fromString(token), null, true) == 1) {
                return new GenericErrorResponseDto<>("User activated!", HttpStatus.OK);
            } else {
                return new GenericErrorResponseDto<>("Not found! Invalid token", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessResourceFailureException e) {
            return new GenericErrorResponseDto<>("Internal Server Error! Try Later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
