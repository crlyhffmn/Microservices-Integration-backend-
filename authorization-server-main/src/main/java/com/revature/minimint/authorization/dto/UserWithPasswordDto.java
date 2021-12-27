package com.revature.minimint.authorization.dto;

import com.revature.minimint.authorization.entity.User.Password;
import com.revature.minimint.authorization.entity.User.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithPasswordDto {
    private long userId;
    private String userEmail;
    private String username;
    private String userPassword;

    public static User getUser(UserWithPasswordDto dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .userEmail(dto.getUserEmail())
                .username(dto.getUsername())
                .build();

        if (dto.userPassword != null) {
            user.setUserPassword(Password.builder()
                    .password(dto.getUserPassword())
                    .build());
        }
        return user;
    }
}
