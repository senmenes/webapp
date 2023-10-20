package com.webapp.demo.controller;

import com.webapp.demo.dto.*;
import com.webapp.demo.model.User;
import com.webapp.demo.model.UserPublicDataOnly;
import com.webapp.demo.service.ActivationService;
import com.webapp.demo.service.LoginService;
import com.webapp.demo.service.SignUpService;
import com.webapp.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private ActivationService activationService;

    @RequestMapping("/users")
    UsersPaginationDto getUsers(PageRequestDto pageRequestDto){
        Pageable paging = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<User> userPage = userService.findAll(paging);
        int totalPage = userPage.getTotalPages();
        List<UserPublicDataOnly> userListPage = userPage.getContent().stream().map(user -> new UserPublicDataOnly(user.getId(),user.getUsername(), user.getEmail())).collect(Collectors.toList());
        return new UsersPaginationDto(userListPage, totalPage);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDto requestDto) {

        return ResponseEntity.ok(signUpService.createUser(User.builder()
                                .email(requestDto.getEmail())
                                .username(requestDto.getUserName())
                                .password(requestDto.getPassword())
                                .build()));
    }

    @PatchMapping("/activation/{pathVariable}")
    public ResponseEntity<?> activateUser(@Valid @PathVariable String pathVariable) {
        GenericErrorResponseDto<String> response = activationService.activateUser(pathVariable);
        return ResponseEntity.status(response.getReturnCode()).body(response.getMessage());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {
        if(loginService.login(request)) {
            return ResponseEntity.ok("");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

}
