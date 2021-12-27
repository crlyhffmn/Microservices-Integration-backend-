package com.revature.minimint.authorization.repository;

import com.revature.minimint.authorization.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUserEmail(String email);

    Optional<User> getUserByUsername(String username);
}