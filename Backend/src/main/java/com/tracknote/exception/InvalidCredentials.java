package com.tracknote.exception;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials() {
        super("Invalid credentials. Please try again");
    }
}
