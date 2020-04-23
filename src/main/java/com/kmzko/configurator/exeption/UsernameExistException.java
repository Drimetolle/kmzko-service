package com.kmzko.configurator.exeption;

public class UsernameExistException extends RuntimeException {
    public UsernameExistException(String errorMessage) {
        super(errorMessage);
    }
}
