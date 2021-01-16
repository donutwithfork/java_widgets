package com.example.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WrongStorageConfigurationException extends Exception {

    private static final long serialVersionUID = 2L;
    private static final String message = "Wrong configuration for widget storage";

    public WrongStorageConfigurationException() {
        super(message);
    }

    public WrongStorageConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongStorageConfigurationException(String message) {
        super(message);
    }
    
    public WrongStorageConfigurationException(Throwable cause) {
        super(cause);
    }

}
