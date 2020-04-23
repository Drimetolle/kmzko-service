package com.kmzko.configurator.exeption;

public class EmailExist extends RuntimeException {
    public EmailExist(String errorMessage) {
        super(errorMessage);
    }
}
