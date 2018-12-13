package com.interswitch.microservice_test.Controller.Res;

public class Error {
    private final String field;
    private final String message;

    public Error(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
