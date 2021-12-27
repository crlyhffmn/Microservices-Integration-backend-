package com.revature.minimint.authorization.service;

import com.revature.minimint.authorization.entity.User.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User addUser(User user);

    User getUserByEmail(String email);

    User getUserById(long id);

    User updateUser(long id, User user);

    User login(User user);

    User getUserByUsername(String username);
}
