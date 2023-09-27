package com.webapp.demo.repository;

import com.webapp.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    User findByEmail(String email);
    User findByUsername(String email);
}
