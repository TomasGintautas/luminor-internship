package com.luminor.internship;

public class BusinessException extends RuntimeException {

    private final String field;

    public BusinessException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}