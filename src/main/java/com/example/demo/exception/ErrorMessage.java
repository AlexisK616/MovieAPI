package com.example.demo.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private final String exception;
    private final String path;
    private final String message;

    public ErrorMessage(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.path = exception.getMessage();
        this.message = path;
    }
}