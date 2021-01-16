package com.example.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WidgetNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    private static final String message = "Widget not found";

    public WidgetNotFoundException() {
        super(message);
    }

    public WidgetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WidgetNotFoundException(String message) {
        super(message);
    }
    
    public WidgetNotFoundException(Throwable cause) {
        super(cause);
    }

}
