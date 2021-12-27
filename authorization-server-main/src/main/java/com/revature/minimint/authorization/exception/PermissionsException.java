package com.revature.minimint.authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PermissionsException extends RuntimeException {
    public PermissionsException(String message) {
        super(message);
    }
}
