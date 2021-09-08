package com.helpy.util;

public enum ExceptionMessageEnum {
    MODEL_NOT_FOUND("Resource not found"),
    INCORRECT_REQUEST("Incorrect request");

    private final String value;

    ExceptionMessageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
