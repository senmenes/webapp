package com.webapp.demo.service;

import com.webapp.demo.model.User;
import com.webapp.demo.model.UserPublicDataOnly;
import com.webapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public User findByActivationToken(UUID token) {
        return userRepository.findByActivationToken(token);
    }

    public int findByActivationTokenAndActivateUser(UUID token, UUID activationToken, Boolean active) {
        return userRepository.findByActivationTokenAndActivateUser(token, activationToken, active);
    }

    public Page<User> findAll(Pageable page){
        return userRepository.findAll(page);
    }

    public User findByPasswordAndEmail(String password, String email) {
        return userRepository.findByPasswordAndEmail(password, email);
    }

    public User findByPasswordAndUsername(String password, String username) {
        return userRepository.findByPasswordAndUsername(password, username);
    }

    public User findByEmailOrUsername(String username) {
        return userRepository.findByEmailOrUsername(username, username);
    }

    public User findByUserNameOrEmailAndPassword(String password, String username) {
        return userRepository.findByUsernameOrEmailAndPassword(username, username, password);
    }
}
