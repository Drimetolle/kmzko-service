package com.kmzko.service.exeption;

public class NotValidConveyorMapException extends RuntimeException {
    public NotValidConveyorMapException(String errorMessage) {
        super(errorMessage);
    }
}
