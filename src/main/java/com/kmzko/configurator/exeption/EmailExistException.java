package com.kmzko.configurator.exeption;

public class EmailExistException extends RuntimeException {
    public EmailExistException(String errorMessage) {
        super(errorMessage);
    }
}
