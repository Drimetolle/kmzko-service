package com.kmzko.configurator.exeption;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(String errorMessage) {
        super(errorMessage);
    }
}
