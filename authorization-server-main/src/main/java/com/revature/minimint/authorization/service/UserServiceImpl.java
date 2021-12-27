package com.revature.minimint.authorization.service;

import com.revature.minimint.authorization.entity.User.Password;
import com.revature.minimint.authorization.entity.User.User;
import com.revature.minimint.authorization.exception.BadRequestException;
import com.revature.minimint.authorization.exception.DuplicateResourceException;
import com.revature.minimint.authorization.exception.NotFoundException;
import com.revature.minimint.authorization.exception.PermissionsException;
import com.revature.minimint.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        Optional<User> checkedUser = userRepository.getUserByUserEmail(user.getUserEmail());
        if (checkedUser.isPresent()) {
            throw new DuplicateResourceException("Email is already in use");
        }

        checkedUser = userRepository.getUserByUsername(user.getUsername());
        if (checkedUser.isPresent()) {
            throw new DuplicateResourceException("Username is already in use");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.getUserByUserEmail(email);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userOptional.get();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userOptional.get();
    }

    @Override
    public User updateUser(long userId, User user) {
        //Check to make sure email isn't in use by another registered user
        Optional<User> userOptional = userRepository.getUserByUserEmail(user.getUserEmail());
        User userCurrent;

        if (userOptional.isPresent()) {
            userCurrent = userOptional.get();
            if (userCurrent.getUserId() != userId) {
                throw new DuplicateResourceException("Email is already in use");
            }
        }

        //Check to make sure new username isn't in use by another registered user
        userOptional = userRepository.getUserByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            userCurrent = userOptional.get();
            if (userCurrent.getUserId() != userId) {
                throw new DuplicateResourceException("Username is already in use");
            }
        }

        if (user.getUserPassword() == null) {
            throw new BadRequestException("No authentication provided");
        }

        if (user.getUserId() == 0 && (user.getUserEmail() == null || user.getUserEmail().equals(""))) {
            throw new NotFoundException("User not found");
        }

        if (userOptional.isEmpty()) {
            userOptional = userRepository.findById(user.getUserId());
            if (userOptional.isEmpty()) {
                throw new NotFoundException("User not found");
            }
        }

        userCurrent = userOptional.get();
        userCurrent.setUsername(user.getUsername());
        userCurrent.setUserEmail(user.getUserEmail());
        //user may or may not be passed with a password
        if (user.getUserPassword() != null && !Objects.equals(user.getUserPassword().getPassword(), "")) {
            userCurrent.setUserPassword(Password.builder()
                    .password(user.getUserPassword().getPassword())
                    .build()
            );
        }

        return userRepository.save(userCurrent);
    }

    @Override
    public User login(User user) {
        User userOutput = checkAuthorized(user);

        return userRepository.save(userOutput);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.getUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userOptional.get();
    }

    private User checkAuthorized(User user) {

        if (user.getUserPassword() == null) {
            throw new BadRequestException("No authentication provided");
        }

        if (user.getUserId() == 0 && (user.getUserEmail() == null || user.getUserEmail().equals(""))) {
            throw new NotFoundException("User not found");
        }

        Optional<User> userOptional = userRepository.getUserByUserEmail(user.getUserEmail());

        if (userOptional.isEmpty()) {
            userOptional = userRepository.findById(user.getUserId());
            if (userOptional.isEmpty()) {
                throw new NotFoundException("User not found");
            }
        }

        User userOutput = userOptional.get();

        if (!userOutput.checkPassword(user.getUserPassword().getPassword())) {
            throw new PermissionsException("Email or password is incorrect");
        }

        return userOutput;
    }
}
