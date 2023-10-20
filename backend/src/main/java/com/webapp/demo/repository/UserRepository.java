package com.webapp.demo.repository;

import com.webapp.demo.model.User;
import com.webapp.demo.model.UserPublicDataOnly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    User findByEmail(String email);
    User findByUsername(String email);
    User findByActivationToken(UUID token);

    @Query(fields = "{id:1, username:1, email:1}", value = "{}")
    Page<User> findAll(Pageable page);

    @Query("{'_id' : ?0}")
    @Update("{'$set': {'activationToken': ?1}}")
    Integer updateActivationToken(String id, UUID activationToken);

    @Query("{'_id' : ?0}")
    @Update("{'$set': {'activate': ?1}}")
    Integer updateActivate(String id, Boolean activate);

    @Query("{'activationToken' : ?0}")
    @Update("{'$set': {'activationToken': ?1," +
                      "'active': ?2}}")
    Integer findByActivationTokenAndActivateUser(UUID token, UUID activationToken, Boolean active);

    User findByPasswordAndEmail(String password, String email);

    User findByPasswordAndUsername(String password, String username);

    User findByUsernameOrEmailAndPassword(String username, String email, String password);

    User findByEmailOrUsername(String email, String username);
}
