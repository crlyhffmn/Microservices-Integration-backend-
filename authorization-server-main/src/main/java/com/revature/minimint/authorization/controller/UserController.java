package com.revature.minimint.authorization.controller;

import com.revature.minimint.authorization.dto.UserForDisplayDto;
import com.revature.minimint.authorization.dto.UserWithPasswordDto;
import com.revature.minimint.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserForDisplayDto addUser(@RequestBody UserWithPasswordDto newUser) {
        return UserForDisplayDto.getDto(userService.addUser(UserWithPasswordDto.getUser(newUser)));
    }

    @PutMapping
    public UserForDisplayDto updateUser(@RequestBody UserWithPasswordDto updatedUser) {
        return UserForDisplayDto.getDto(
                userService.updateUser(
                        updatedUser.getUserId(),
                        UserWithPasswordDto.getUser(updatedUser)
                ));
    }

    @GetMapping("/id/{userId}")
    public UserForDisplayDto getUserById(@PathVariable("userId") long userId) {
        return UserForDisplayDto.getDto(userService.getUserById(userId));
    }

    @PostMapping("/email")
    public UserForDisplayDto getUserByEmail(@RequestBody String email) {
        return UserForDisplayDto.getDto(userService.getUserByEmail(email));
    }

    @GetMapping("/username/{username}")
    public UserForDisplayDto getUserByUsername(@PathVariable("username") String username) {
        return UserForDisplayDto.getDto(userService.getUserByUsername(username));
    }

    @PostMapping("/login")
    public UserForDisplayDto login(@RequestBody UserWithPasswordDto user) {
        return UserForDisplayDto.getDto(userService.login(UserWithPasswordDto.getUser(user)));
    }
}
