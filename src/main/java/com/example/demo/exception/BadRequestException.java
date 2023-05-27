package com.example.demo.exception;

public class BadRequestException extends RuntimeException {
    private final static String DESCRIPTION = "BAD REQUEST(400)";
    public BadRequestException(String detail){
        super(DESCRIPTION + ':'+ detail);
    }
}