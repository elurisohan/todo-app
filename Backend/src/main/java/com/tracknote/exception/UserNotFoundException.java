package com.tracknote.exception;

import com.tracknote.dto.AuthRequest;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("User with username '%s' not found",username));
    }

}
