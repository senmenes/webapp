package com.webapp.demo.repository;

import com.webapp.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
}
