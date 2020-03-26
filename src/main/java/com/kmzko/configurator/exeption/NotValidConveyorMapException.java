package com.kmzko.configurator.exeption;

public class NotValidConveyorMapException extends RuntimeException {
    public NotValidConveyorMapException(String errorMessage) {
        super(errorMessage);
    }
}
