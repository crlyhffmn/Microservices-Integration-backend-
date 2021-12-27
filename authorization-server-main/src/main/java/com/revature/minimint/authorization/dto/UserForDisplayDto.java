package com.revature.minimint.authorization.dto;

import com.revature.minimint.authorization.entity.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserForDisplayDto {
    private long userId;
    private String userEmail;
    private String username;

    public static UserForDisplayDto getDto(User user) {
        return UserForDisplayDto.builder()
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .username(user.getUsername())
                .build();
    }

    public static User getUser(UserForDisplayDto dto) {
        return User.builder()
                .userId(dto.getUserId())
                .userEmail(dto.getUserEmail())
                .username(dto.getUsername())
                .build();
    }
}
